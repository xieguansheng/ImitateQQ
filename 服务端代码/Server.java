package ImitateQQ;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class Server  extends Thread{
	static int read_id;
	static int len;
	static String read_user_name;
	static String read_user_password;
	static int id;
	static String return_msg="";
	static int photoid;
	static int rec_id;
	static String msg;
	static String oldpwd;
	static String newpwd;
	static String checkpwd;
	static int setpwd_id;
	public static void main(String[] args) throws Exception{
		ServerSocket ss = new ServerSocket(8000);
		MysqlMethod sql = new MysqlMethod();
		HashMap<Integer,Socket> socketmap = new HashMap<Integer,Socket>();
//		Server server = new Server();
		while(true){
			Socket socket = ss.accept();
			
//			System.out.println("fsd");
			Thread thread = new Thread(){
				public void run(){
					while(true){
//					System.out.println(2);
					InputStream input;
					OutputStream output = null;
					DataInputStream dis = null;
//					System.out.println(4);
					try {
						input = socket.getInputStream();
						output = socket.getOutputStream();
						dis = new DataInputStream(input);
//						System.out.println(5);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						getreadid(dis);
//						System.out.println(6);
					} catch (SocketException e1) {
						// TODO Auto-generated catch block
//						e1.printStackTrace();
					}
					
					if(read_id==Constants.regid){
						unpackRegmsg(dis);
//						System.out.println(read_user_name+"  "+read_user_password);
						id=(int)(Math.random()*10000+Math.random()*100);

						sql.insert("userinfo",id, read_user_name, read_user_password, 0,photoid);
						return_msg="注册成功";
						Connection con = Conn.getConnection();
						Statement stat = null;
						try {
							stat = con.createStatement();
							stat.executeUpdate("create table "+read_user_name+"_friends"+"(id int, username varchar(80), photoid int,mark int)");  
//							stat.executeUpdate("create tabel test(id int, name varchar(80))");
//							stat.executeUpdate("insert into test values(1, '张三')");  
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally{
							try {
								con.close();
								stat.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					else if(read_id==Constants.logid){
						unpackLogmsg(dis);
						id=Integer.parseInt(read_user_name);
						sql.id=id;
						boolean flag =sql.query2("userinfo");
//						System.out.println(flag);
						if(flag){
//							System.out.println(read_user_password+"  "+sql.passwd);
							if(read_user_password.equals(sql.passwd)){
								return_msg="登陆成功";
								socketmap.put(id, socket);
							}
							else{
								return_msg="密码错误";
							}
						}
						else{
							return_msg="用户名不存在";
						}
					}
					else if(read_id==Constants.setpwdid){
						unpackpwdmsg(dis);
						sql.id=setpwd_id;
						boolean flag =sql.query2("userinfo");
//						System.out.println(flag);
						if(flag){
							if(!oldpwd.equals(sql.passwd)){
//								System.out.println(oldpwd+"   "+sql.passwd);
								return_msg="密码输入错误";
							}
							else{
								sql.update("userinfo", sql.id,newpwd);
								return_msg="密码修改成功";
							}
						}
					}
					else if(read_id==Constants.chatid){
						unpackchatmsg(dis);
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						DataOutputStream dos = new DataOutputStream(bos);
						try {
//							System.out.println("fasongchenggong");
							byte[] res_name =read_user_name.getBytes();
							byte[] chatmsg =msg.getBytes();
							dos.writeInt(Constants.res_chatid);
							dos.writeInt(rec_id);
							dos.writeInt(id);
							dos.writeInt(24+res_name.length+chatmsg.length);
						
							dos.writeInt(res_name.length);
							dos.write(res_name);
					
							dos.writeInt(chatmsg.length);
							dos.write(chatmsg);
							
							OutputStream chatstream = socketmap.get(rec_id).getOutputStream();
							byte[] stream = bos.toByteArray();
							chatstream.write(stream);
							chatstream.flush();
							output.write(stream);
							output.flush();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
//					System.out.println("ggds");
					if(read_id==Constants.logid||read_id==Constants.regid||read_id==Constants.setpwdid){
					try {
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						DataOutputStream dos = new DataOutputStream(bos);
						byte[] re_msg = return_msg.getBytes();
//						System.out.println(id);
						dos.writeInt(Constants.res_reglogid);
						dos.writeInt(re_msg.length);
						dos.write(re_msg);
						if(return_msg.equals("注册成功")){
							dos.writeInt(id);
						}
						else if(return_msg.equals("登陆成功")){
							dos.writeInt(sql.id);
							dos.writeInt(sql.photoid);
							byte[] res_name = sql.username.getBytes();
							dos.writeInt(res_name.length);
							dos.write(res_name);
							MysqlMethod log_method = new MysqlMethod();
							int count = log_method.getcount(sql.username);
//							System.out.println(count);
							dos.writeInt(count);
							
							Connection conn = null;
							PreparedStatement ps = null;
							ResultSet rs = null;
							try {
								conn = Conn.getConnection();
								//预编译sql
								ps = conn.prepareStatement("select * from "+sql.username+"_friends"+" where mark=?");
								ps.setInt(1,0);
								rs = ps.executeQuery();
								
//								System.out.println(flag);
//								rs = st.executeQuery("select * from userinfo where username='admin'");
//								rs = st.executeQuery("select * from userinfo order by username desc");
								while(rs.next()) {
									 int friendid = rs.getInt("id");
									 String friendname = rs.getString("username");
									 int friendphotoid = rs.getInt("photoid");
									 dos.writeInt(friendid);
									 dos.writeInt(friendphotoid);
									 byte[] fri_name = friendname.getBytes();
									 dos.writeInt(fri_name.length);
									 dos.write(fri_name);
								}
							} catch (Exception ex) {
								ex.printStackTrace();
							} finally {
								MysqlMethod.close(conn, ps, rs);
								
							}
						}
						
						output.write(bos.toByteArray());
						output.flush();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						
					}
					}
					read_id=0;
					}
				}
			};
			thread.start();
		}
	}
	public static void getreadid(DataInputStream dis)throws SocketException{
		
			try {
				read_id=dis.readInt();
			} catch (IOException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
	}
	public static void unpackRegmsg(DataInputStream dis) {
		// TODO Auto-generated method stub
		try {
			photoid = dis.readInt();
			len=dis.readInt();
			

			byte[] nbyte = new byte[dis.readInt()];
			dis.readFully(nbyte);
			read_user_name=new String(nbyte);
			
			byte[] pbyte = new byte[dis.readInt()];
			dis.readFully(pbyte);
			read_user_password=new String(pbyte);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void unpackLogmsg(DataInputStream dis) {
		// TODO Auto-generated method stub
		try {
			len=dis.readInt();
			byte[] nbyte = new byte[dis.readInt()];
			dis.readFully(nbyte);
			read_user_name=new String(nbyte);
			
			byte[] pbyte = new byte[dis.readInt()];
			dis.readFully(pbyte);
			read_user_password=new String(pbyte);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}
	public static void unpackchatmsg(DataInputStream dis) {
		try {
			id=dis.readInt();
			byte[] nbyte = new byte[dis.readInt()];
			dis.readFully(nbyte);
			read_user_name=new String(nbyte);
			
			rec_id=dis.readInt();
			byte[] pbyte = new byte[dis.readInt()];
			dis.readFully(pbyte);
			msg=new String(pbyte);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block

		}
	}
	public static void unpackpwdmsg(DataInputStream dis) {
		// TODO Auto-generated method stub
		try {
			setpwd_id=dis.readInt();
			byte[] nbyte = new byte[dis.readInt()];
			dis.readFully(nbyte);
			oldpwd=new String(nbyte);
			
			byte[] pbyte = new byte[dis.readInt()];
			dis.readFully(pbyte);
			newpwd=new String(pbyte);
			
			byte[] pwdbyte = new byte[dis.readInt()];
			dis.readFully(pwdbyte);
			checkpwd=new String(pwdbyte);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
