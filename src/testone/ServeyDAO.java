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
public boolean insertInfo(ServeyVO vo) throws SQLException{
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

public void insertServey(String servey) throws SQLException{
	String sql = "insert into \"SERVEY\" values (?, \"SERVEY_CODE\".nextval, ?)";
	try {
		conn = jdbcTemplate.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString (1, servey); 
		pstmt.setLong(2, 1);
		pstmt.executeUpdate();
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

public List<ServeyVO> selectAll(int selectquery)throws SQLException{
	System.out.println("------------------------------------------------------------------------");
	System.out.println("\t장르 순위");
	String sql="";
	if(selectquery==1) {
		sql= "select  * from \"SERVEY\"order by \"SERVEY_CODE\" ";
	}else if(selectquery==2) {
		sql= "select  * from \"SERVEY\"order by \"SERVEY_COUNT\" desc ";
	}
	List<ServeyVO> ls = new ArrayList<>();
	
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
public void updateInfo(int values) throws SQLException{
 
	String sql="update \"SERVEY\" set \"SERVEY_COUNT\"=(select \"SERVEY_COUNT\" from \"SERVEY\" where  \"SERVEY_CODE\"="+values+")+1" +" where \"SERVEY_CODE\"="+values +""; 	
	try {
		conn = jdbcTemplate.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	} 
}
public boolean selectServeyName(String serveyname)throws SQLException{
	String sql = "select  * from \"SERVEY\"where  \"SERVEY_NAME\"= ?";
	boolean stat=false;
	try {
		conn = jdbcTemplate.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, serveyname);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			stat=false;
		}else if(!rs.next()) {
			stat=true;
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
	return stat;
}

public int lastServey_Code() throws SQLException{
	int ok=0;
	String sql= "select  \"SERVEY_CODE\" from \"SERVEY\" where ROWNUM<=1 order by \"SERVEY_CODE\" desc ";
	try {
		conn = jdbcTemplate.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		rs.next();
		ok=rs.getInt("SERVEY_CODE");
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
	return ok;
	
}

}
