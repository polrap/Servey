package testone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JoinDAO extends DataBaseConnectResource{
	public JoinDAO() {
		jdbcTemplate = JdbcTemplate.getInstance();
	}
	public List<JoinVO> selectAll()throws SQLException{
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t나이 대 별 장르 순위 결과 입니다.");
	String sql="select \"US\".\"AGE\",  \"SV\".\"SERVEY_NAME\",  sum(\"COUNT\") as\"AGECOUNT\" "
			+ "from \"USER\" \"US\" join \"SERVEY\" \"SV\" on (\"US\".\"SERVEY_CODE\"=\"SV\".\"SERVEY_CODE\")  "
			+ " group by \"SV\".\"SERVEY_NAME\",\"US\".\"AGE\" order by \"US\".\"AGE\" desc, \"AGECOUNT\" desc";
		
		List<JoinVO> ls = new ArrayList<>();
		
		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				JoinVO tmp = new JoinVO(
						rs.getLong("AGE"),
						rs.getString("SERVEY_NAME"),
						rs.getLong("AGECOUNT"));
			ls.add(tmp);			
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return (ls.size() == 0) ? null : ls;
	}
	
	public List<JoinVO> selectThree()throws SQLException{
		System.out.println("------------------------------------------------------------------------");
		String sql="select  \"SV\".\"SERVEY_NAME\" as\"SERVEYNAME\",  \"SG\".\"SONGNAME\" as\"SNAME\",\"SG\".\"SONGCOUNT\" as\"SONGCOUNT\"from \r\n" + 
				"\"SONG\" \"SG\"  join \"SERVEY\" \"SV\"\r\n" + 
				" on \"SG\".\"SERVEY_CODE\"= \"SV\".\"SERVEY_CODE\"\r\n" + 
				"order by \"SV\".\"SERVEY_COUNT\"desc,\"SG\".\"SONGCOUNT\" desc";
			
			List<JoinVO> ls = new ArrayList<>();
			
			try {
				conn = jdbcTemplate.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery(); //쿼리 전송!
				while(rs.next()) {
					JoinVO tmp = new JoinVO(
							rs.getString("SERVEYNAME"),
							rs.getString("SNAME"),
							rs.getLong("SONGCOUNT"));
				ls.add(tmp);			
				}
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				if(rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return (ls.size() == 0) ? null : ls;
		}

	public List<JoinVO> selectGender(int choiceGender)throws SQLException{
		char gender=' ';
		if(choiceGender==1) {
			gender='M';
		}else if(choiceGender==2) {
			gender='F';
		}
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\t성별 장르 순위 결과 입니다.");
	String sql="select \"SV\".\"SERVEY_NAME\", sum(\"US\".\"COUNT\") as\"SUMCOUNT\"" + 
			"from \"SERVEY\" \"SV\" join \"USER\" \"US\" on \"SV\".\"SERVEY_CODE\"=\"US\".\"SERVEY_CODE\" where \"US\".\"GENDER\"='"+gender+"'" +"group by \"SV\".\"SERVEY_NAME\", \"US\".\"GENDER\"order by \"SUMCOUNT\" desc";
		
		List<JoinVO> ls = new ArrayList<>();
		
		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				JoinVO tmp = new JoinVO(
						rs.getString("SERVEY_NAME"),
						rs.getLong("SUMCOUNT"));
			ls.add(tmp);			
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return (ls.size() == 0) ? null : ls;
	}
}
