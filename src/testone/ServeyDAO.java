package testone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	String sql = "insert into \"SERVEY\" values (?, \"SERVEY_CODE\".nextval, ?)";
	try {
		conn = jdbcTemplate.getConnection();
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString (1, vo.getServeyname()); 
		pstmt.setLong(2, vo.getServeycount());
		int result = pstmt.executeUpdate();
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
public void insertInfo(String servey) {
	String sql = "insert into \"SERVEY\" values (?, \"SERVEY_CODE\".nextval, ?)";
	try {
		conn = jdbcTemplate.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString (1, servey); 
		pstmt.setLong(2, 1);
		int result = pstmt.executeUpdate();
		System.out.println(result + "행이 삽입 되었습니다.");
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
}
public List<ServeyVO> selectAll(){
	List<ServeyVO> ls = new ArrayList<>();
	
	String sql = "select  * from \"SERVEY\"order by \"SERVEY_CODE\" ";
	try {
		conn = jdbcTemplate.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery(); //쿼리 전송!
		while(rs.next()) {
			ServeyVO tmp = new ServeyVO(
					rs.getString("SERVEY_NAME"),
					rs.getLong("SERVEY_CODE"),
					rs.getLong("SERVEY_COUNT"));
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
public void updateInfo(int values) {
 
	String sql="update \"SERVEY\" set \"SERVEY_COUNT\"=(select \"SERVEY_COUNT\" from \"SERVEY\" where  \"SERVEY_CODE\"="+values+")+1" +" where \"SERVEY_CODE\"="+values +""; 

	List<ServeyVO> ls = new ArrayList<>();
	
	try {
		conn = jdbcTemplate.getConnection();
		pstmt = conn.prepareStatement(sql);
		int result = pstmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	} 
}

}
