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
			
			pstmt = conn.prepareStatement(sql); //���� ���ø� �غ�
			pstmt.setString (1, vo.getServeyname()); 
			pstmt.setLong(2, vo.getServeycount());
			int result = pstmt.executeUpdate(); //���� ����!
			System.out.println(result + "���� ���� �Ǿ����ϴ�.");
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
