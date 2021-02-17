package com.bhbfc.ism.dao.login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.bhbfc.ism.model.login.Login;

@Repository
public class LoginDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Login> getUser(String userId) {

		String sql = "SELECT FULL_NAME, USER_EMAIL FROM TABLE_NAME WHERE USER_ID = '" + userId + "'";

		List<Login> loginList = (List<Login>) jdbcTemplate.query(sql, new ResultSetExtractor<List<Login>>() {

			@Override
			public List<Login> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Login> list = new ArrayList<Login>();
				while (rs.next()) {
					Login login = new Login();

					login.setFullName(rs.getString("FULL_NAME"));
					login.setUserEmail(rs.getString("USER_EMAIL"));
					list.add(login);
				}
				return list;
			}

		});

		return loginList;
	}

	public Login logIn(String userId, String password) {

		Login login = new Login();

		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withSchemaName("SCHEMA_NAME").withProcedureName("PROCEDURE_NAME");
		
		jdbcCall.addDeclaredParameter(new SqlParameter("in_comp_code", Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlParameter("in_userid", Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlParameter("in_password", Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlParameter("in_ipaddress", Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter("out_login_success", Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter("out_userid", Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter("out_sessionid", Types.VARCHAR));
		jdbcCall.addDeclaredParameter(new SqlOutParameter("out_code", Types.INTEGER));
		jdbcCall.addDeclaredParameter(new SqlOutParameter("out_message", Types.VARCHAR));

		MapSqlParameterSource inParams = new MapSqlParameterSource();
		inParams.addValue("in_comp_code", "001");
		inParams.addValue("in_userid", userId);
		inParams.addValue("in_password", password);
		inParams.addValue("in_ipaddress", "001");
		
		Map<String, Object> outParams = jdbcCall.execute(inParams);
		login.setUserId((String) outParams.get("out_userid"));
		login.setUserEmail((String) outParams.get("out_sessionid"));
		login.setOutCode(""+(Integer) outParams.get("out_code"));
		login.setOutMessage((String) outParams.get("out_message"));
		
		return login;
	}
}
