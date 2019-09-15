package ImitateQQ;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlMethod {
	int id;
	String username;
	String passwd;
	int status;
	int photoid;
	public void query() {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = Conn.getConnection();
			st = conn.createStatement();
//			rs = st.executeQuery("select * from userinfo where username='admin'");
			rs = st.executeQuery("select * from userinfo order by username desc");
			while(rs.next()) {
				 id = rs.getInt("id");
				 username = rs.getString("username");
				 passwd = rs.getString("passwd");
				 status = rs.getInt("status");
				System.out.println("id="+id+",username="+username+",passwd="+passwd + ",status="+status
						);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(conn, st, rs);
		}
	}
	public int getcount(String username){
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		try {
		conn = Conn.getConnection();
		st=conn.createStatement();
		rs =st.executeQuery("select count(*) as result from "+username+"_friends");
		//创建变量存取个数
		while(rs.next())
		{
			count=rs.getInt(1);
		}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(conn, st, rs);
		}
		return count;
	}
	public boolean query2(String tablename) {
		//sql注入 name = '\' or 1=1'
//		String sql = "select * from userinfo where username='"+name+"'";
//		System.out.println(sql);
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag=false;
		try {
			conn = Conn.getConnection();
			//预编译sql
			ps = conn.prepareStatement("select * from "+tablename+" where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			flag = rs.next();
//			System.out.println(flag);
//			rs = st.executeQuery("select * from userinfo where username='admin'");
//			rs = st.executeQuery("select * from userinfo order by username desc");
			if(flag) {
				 id = rs.getInt("id");
				 username = rs.getString("username");
				 passwd = rs.getString("passwd");
				 photoid = rs.getInt("photoid");
//				 status = rs.getInt("status");
//				System.out.println("id="+id+",username="+username+",passwd="+passwd+",photoid="+photoid);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return flag;


	}
	
	public int insert(String tablename,int id,String name,String passwd,int status,int photoid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = Conn.getConnection();
			//预编译sql
			ps = conn.prepareStatement("insert into "+tablename+"(id,username,passwd,status,photoid) values(?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, passwd);
			ps.setInt(4, status);
			ps.setInt(5, photoid);
			int n = ps.executeUpdate();
			return n;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return 0;
	}
	public int addinsert(String tablename,int id,String name,int photoid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = Conn.getConnection();
			//预编译sql
			ps = conn.prepareStatement("insert into "+tablename+"(id,username,photoid,mark) values(?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setInt(3, photoid);
			ps.setInt(4, 0);
			int n = ps.executeUpdate();
			return n;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return 0;
	}
	public int update(String tablename,int id,String passwd) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = Conn.getConnection();
			//预编译sql
			ps = conn.prepareStatement("update "+tablename+" set passwd=? where id=?");
			ps.setString(1, passwd);
			ps.setInt(2, id);
			int n = ps.executeUpdate();
			return n;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return 0;
	}
	
	public int delete(String tablename,String name) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = Conn.getConnection();
			//预编译sql
			ps = conn.prepareStatement("delete from "+tablename+" where username like 'nam%'");
//			ps.setString(1, name);
			int n = ps.executeUpdate();
			return n;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close(conn, ps, rs);
		}
		return 0;
	}
	
	protected static void close(Connection conn, Statement st, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

//	public static void main(String[] args) {
//		MysqlMethod t = new MysqlMethod();
//		t.update("name8", "88888888");
//		int n = t.delete("name5");
//		System.out.println("删除�??"+n+"�??");
		
//		String name ;
//		= "' or 1=1 or '1'='1";
//		id ="fg";
//		t.query2();
//		for(int i=0; i<10; i++) {
//			t.insert("name"+i, "passwd"+i, 0);
//		}
//	}

}
