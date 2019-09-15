package ImitateQQ;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class qqclient extends Thread{
	static int id;
	int photoid;
	static String username;
	int type_id;
	int read_id;
	int send_id;
	String send_name;
	String msg;
	JTextArea log;
	
	private Socket socket;
	private InputStream inputStream;
	private OutputStream outputStream;
	public boolean init() {
		try {
			socket = new Socket("127.0.0.1", 8000);
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
			rec_thread.start();
//			Random random = new Random();
//			outputStream.write(("张三"+random.nextInt(100)+"\n").getBytes("gbk"));
//			outputStream.flush();
			//启动接受线程
//			startRecvThread();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public void getlog(JTextArea log){
		this.log = log;
	}
	public void sendmsg(Message msg){
		try {
//			System.out.println(msg.type_id+"  ");
			outputStream.write(msg.pack());
			outputStream.flush();
//			JOptionPane.showMessageDialog(null, "登陆成功");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	String rec_msg;
	static DataInputStream dis;
	Thread rec_thread = new Thread(){
	public void run(){
		while(true){
			dis = new DataInputStream(inputStream);
			try {
				read_id=dis.readInt();
//				System.out.println(read_id);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(read_id==Constants.res_reglogid){
			unpack(dis);
			if(rec_msg.equals("注册成功")){
				MainUI.Regjf.dispose();
				rec_msg = "注册成功，您的qq号是"+id;
				JOptionPane.showMessageDialog(null,rec_msg);
			}
			else if(rec_msg.equals("登陆成功")){
				JOptionPane.showMessageDialog(null,rec_msg);
				MainUI.userui(id,photoid,username);
				MainUI.loginFrame.setVisible(false);
				rec_msg=null;
				
				MainUI.friendshow(dis);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				MainUI.friendjp.removeAll();
				MainUI.friendjp.repaint();
						
					}
			else{
				JOptionPane.showMessageDialog(null,rec_msg);
			}
			}
			else if(read_id==Constants.res_chatid){
//				System.out.println("jieshouchenggong");
				try {
					int rec_id = dis.readInt();
					send_id = dis.readInt();
					if(rec_id==id||send_id==id){
						int length = dis.readInt();
						byte[] name = new byte[dis.readInt()];
						dis.readFully(name);
						send_name = new String(name);
						
						byte[] message = new byte[dis.readInt()];
						dis.readFully(message);
						msg = new String(message);
//						log.append(send_name+"\r\n"+msg);
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//				         System.out.println(df.format(new Date()));
						log.setText(log.getText()+" "+send_name+"  "+df.format(new Date())+"\r\n"+"   "+msg+"\r\n");

//						System.out.println(send_name+"\r\n"+msg);
					}
					else{
						byte[] b = new byte[dis.readInt()];
						dis.read(b);
//						System.out.println("未打�?好友qq");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			}
	}
	public void unpack(DataInputStream dis){
		try {
			byte[] msg = new byte[dis.readInt()];
			dis.readFully(msg);
			rec_msg=new String(msg);
			
			if(rec_msg.equals("注册成功")){
				id = dis.readInt();
			}
			else if(rec_msg.equals("登陆成功")){
				id = dis.readInt();
				photoid = dis.readInt();
				byte[] name = new byte[dis.readInt()];
				dis.readFully(name);
				username = new String(name);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	};
	
}
