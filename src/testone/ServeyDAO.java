package testone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class ServeyDAO {
	private JdbcTemplate jdbcTemplate;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public ServeyDAO() {
		jdbcTemplate = JdbcTemplate.getInstance();
	}
	public boolean insertInfo(ServeyVO vo) {
		boolean ret = false;
		String sql = "insert into \"SERVEY\" values (?, \"SERVEY_COUNT\".nextval, ?)";
		try {
			conn = jdbcTemplate.getConnection();
			
			pstmt = conn.prepareStatement(sql); //쿼리 템플릿 준비
			pstmt.setString (1, vo.getServeyname()); 
			pstmt.setLong(2, vo.getServeycount());
			int result = pstmt.executeUpdate(); //쿼리 전송!
			System.out.println(result + "행이 삽입 되었습니다.");
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
