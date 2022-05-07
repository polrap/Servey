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
	public boolean insertUser(UserVO vo) throws SQLException{
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
			System.out.println("보기 사항에 없는 번호를 입력 하셨습니다.\n 첫 화면으로 돌아가겠습니다.");
			ret= false;
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
