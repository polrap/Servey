package testone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	private JdbcTemplate jdbcTemplate;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public UserDAO() {
		jdbcTemplate = JdbcTemplate.getInstance();
	}
	public boolean insertUser(UserVO vo) {
		boolean ret = false;
		String sql = "insert into \"USER\" values (?, ?, ?)";
		try {
			conn = jdbcTemplate.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong (1, vo.getServeyCode()); 
			pstmt.setLong(2, vo.getAge());
			pstmt.setString(3, String.valueOf(vo.getGender()));
			
			int result = pstmt.executeUpdate();
			System.out.println(result + "의 user info가 삽입 되었습니다.");
			ret = true;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
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
		return ret;
	}
}
