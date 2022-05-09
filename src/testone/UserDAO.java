package testone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		String sql = "insert into \"USER\" values (?, ?, ?,1)";
		try {
			conn = jdbcTemplate.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong (1, vo.getServeyCode()); 
			pstmt.setLong(2, vo.getAge());
			pstmt.setString(3, String.valueOf(vo.getGender()));
			
			int result = pstmt.executeUpdate();
			System.out.println(result + "�� user info�� ���� �Ǿ����ϴ�.");
			ret = true;
		} catch(SQLException e) {
			System.out.println("���� ���׿� ���� ��ȣ�� �Է� �ϼ̽��ϴ�.\nù ȭ������ ���ư��ڽ��ϴ�.");
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
	public boolean selectUser(long code, long age, char GENDER) {
		String sql = "select  * from \"USER\"where  \"SERVEY_CODE\"= ? and \"AGE\"=? and \"GENDER\"=? " ;
		boolean stat=false;
		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, code);
			pstmt.setLong(2, age);
			pstmt.setString(3, String.valueOf(GENDER));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				stat=true;
			}else if(!rs.next()) {
				stat=false;
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
		System.out.println("�˻� �Ϸ�");
		return stat;
	}
	public int returnUserCount(UserVO uvo) {
		String sql = "select  * from \"USER\"where  \"SERVEY_CODE\"= ? and \"AGE\"=? and \"GENDER\"=? " ;
		int ok=0;
		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, uvo.getServeyCode());
			pstmt.setLong(2, uvo.getAge());
			pstmt.setString(3, String.valueOf(uvo.getGender()));
			rs = pstmt.executeQuery();
			rs.next();
			ok=rs.getInt("COUNT");
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
		System.out.println("�˻� �Ϸ�");
		return ok;
	}
	public boolean updateUserCount(UserVO uvo, long countValue) throws SQLException{
		 boolean ret;
		String sql="update \"USER\" set \"COUNT\"=(select \"COUNT\" from \"USER\" "
				+ "where  \"SERVEY_CODE\"="+uvo.getServeyCode()+" and \"AGE\"="+uvo.getAge() +" and \"GENDER\"='"+uvo.getGender()+"')+1"
				+"where \"SERVEY_CODE\"="+uvo.getServeyCode() +" and \"AGE\"="+uvo.getAge() +" and \"GENDER\"='"+uvo.getGender()+"'"; 

		List<ServeyVO> ls = new ArrayList<>();
		
		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			int result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		ret = true;
		return ret;
	}
	
}
