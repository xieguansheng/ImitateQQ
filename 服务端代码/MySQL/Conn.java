package ImitateQQ;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {
	private static String dbURL = "jdbc:mysql://localhost:3306/xqq?useSSL=false&serverTimezone=UTC&characterEncoding=utf-8";
	private static String dbUser = "root";
	private static String dbPasswd = "1234";
	
	public static Connection getConnection() {
		try {
			//加载mysql驱动�??
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(dbURL,dbUser,dbPasswd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
