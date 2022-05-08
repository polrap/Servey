package testone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JoinDAO {
	private JdbcTemplate jdbcTemplate;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public JoinDAO() {
		jdbcTemplate = JdbcTemplate.getInstance();
	}
	public List<JoinVO> selectAll()throws SQLException{
	String sql="select distinct \"US\".\"AGE\" as \"USEAGE\",\"SV\".\"SERVEY_NAME\" as\"SERVEYNAME\", \"SV\".\"SERVEY_COUNT\" as\"SERVEYCOUNT\" from \r\n" + 
				"\"USER\" \"US\" join \"SERVEY\" \"SV\" on  \"US\".\"SERVEY_CODE\"= \"SV\".SERVEY_CODE \r\n" + 
				"order by \"US\".\"AGE\" desc, \"SV\".\"SERVEY_COUNT\" desc";
		
		List<JoinVO> ls = new ArrayList<>();
		
		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); //���� ����!
			while(rs.next()) {
				JoinVO tmp = new JoinVO(
						rs.getLong("USEAGE"),
						rs.getString("SERVEYNAME"),
						rs.getLong("SERVEYCOUNT"));
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
		String sql="select  \"SV\".\"SERVEY_NAME\" as\"SERVEYNAME\",  \"SG\".\"SONGNAME\" as\"SNAME\",\"SG\".\"SONGCOUNT\" as\"SONGCOUNT\"from \r\n" + 
				"\"SONG\" \"SG\"  join \"SERVEY\" \"SV\"\r\n" + 
				" on \"SG\".\"SERVEY_CODE\"= \"SV\".\"SERVEY_CODE\"\r\n" + 
				"order by \"SV\".\"SERVEY_COUNT\"desc,\"SG\".\"SONGCOUNT\" desc";
			
			List<JoinVO> ls = new ArrayList<>();
			
			try {
				conn = jdbcTemplate.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery(); //���� ����!
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
}
