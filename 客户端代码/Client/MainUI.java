package ImitateQQ;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.DataInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
public class MainUI extends Thread{
	static qqclient qc = new qqclient();
	static JFrame loginFrame = new JFrame();
	static LoginMsg logmsg = new LoginMsg();
	private void mainui(){
		
		qc.init();
		
		loginFrame.setSize(550, 500);
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		Dimension imagesizi = new Dimension(600,200);
		
		ImageIcon image = new ImageIcon("C:\\Users\\guansheng\\Desktop\\coding-materials\\imitate QQ\\login.jpg");
		Image i = image.getImage();
		Image seti = i.getScaledInstance(600, 200,0);
		image.setImage(seti);
		
		JLabel iconlabel = new JLabel(image);
//		iconlabel.setPreferredSize(imagesizi);
		loginFrame.add(iconlabel,BorderLayout.NORTH);
		
		
		JPanel spanel = new JPanel();
		Dimension btnsize  = new Dimension(250,40);
		
		
		JButton LoginButton = new JButton("登陆");
		LoginButton.setPreferredSize(btnsize);
		
//		LoginButton.setBorderPainted(false);
		spanel.add(LoginButton);
		FlowLayout f=(FlowLayout)spanel.getLayout();
		f.setVgap(20);
		
		loginFrame.add(spanel,BorderLayout.SOUTH);
		
		JTextField nameinput = new JTextField(20);
		Dimension nisize = new Dimension(300,40);
		JPanel centerpanel1 = new JPanel();
		JPanel centerpanel = new JPanel();
		JPanel centerpanel2 = new JPanel();
		JPasswordField pwinput = new JPasswordField(20);
		JButton Rbutton = new JButton("注册账号");
		JButton Fbutton = new JButton("找回密码");
		Dimension boxsize = new Dimension(100,30);
		JPanel centerpanel3 = new JPanel();
		
		JLabel text1 = new JLabel("账号�? ");
		JLabel text2 = new JLabel("密码�? ");
		text1.setFont(new Font("宋体", Font.PLAIN, 25));
		text2.setFont(new Font("宋体", Font.PLAIN, 25));
		
		
		nameinput.setPreferredSize(nisize);
		pwinput.setPreferredSize(nisize);
		Rbutton.setPreferredSize(boxsize);
		Fbutton.setPreferredSize(boxsize);
		
		centerpanel3.add(Rbutton);
		centerpanel3.add(Fbutton);
		
		centerpanel1.add(text1);
		centerpanel1.add(nameinput);
		
		centerpanel2.add(text2);
		centerpanel2.add(pwinput);
		
		centerpanel.add(centerpanel1);
		centerpanel.add(centerpanel2);
		centerpanel.add(centerpanel3);
		
		loginFrame.add(centerpanel,BorderLayout.CENTER);
		loginFrame.setVisible(true);

		Rbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				loginFrame.setVisible(false);
				Registerui();
				
			}
		});
		LoginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logmsg.type_id=Constants.logid;
				logmsg.user_name=nameinput.getText();
				logmsg.user_password=String.valueOf(pwinput.getPassword());
				qc.sendmsg(logmsg);
				System.out.println("登陆发�?�请�?");
			}
		});
//		blistener.nameinput=nameinput;
//		blistener.passwordinput=pwinput;
	}
	
	static JFrame Regjf ;
//	HashMap<String, String> map = new HashMap<String, String>();	
	public void Registerui(){
		Regjf=new JFrame();
		Regjf.setSize(380, 250);
		Regjf.setLocationRelativeTo(null);
		Regjf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Regjf.setLayout(new FlowLayout());
		
		JPanel namejp =new JPanel();
		JPanel photojp = new JPanel();
		JPanel passwdjp = new JPanel();
		JPanel btnjp = new JPanel();
		photojp.setSize(380, 40);
		
		JComboBox<ImageIcon>  box = new JComboBox<ImageIcon>();
//		ImageIcon photo1 = new ImageIcon("C:\\Users\\guansheng\\Desktop\\coding-materials\\imitate QQ\\1.jpg");
//		ImageIcon photo2 = new ImageIcon("C:\\Users\\guansheng\\Desktop\\coding-materials\\imitate QQ\\2.jpg");
//		ImageIcon photo3 = new ImageIcon("C:\\Users\\guansheng\\Desktop\\coding-materials\\imitate QQ\\3.jpg");
		for(int i=0;i<3;i++){
			ImageIcon photo = new ImageIcon("C:\\Users\\guansheng\\Desktop\\coding-materials\\imitate QQ\\"+i+".jpg");
			Image image = photo.getImage().getScaledInstance(30,30,0);
			photo.setImage(image);
			box.addItem(photo);
		}
//		box.addItem(photo1);
//		box.addItem(photo2);
//		box.addItem(photo3);
		
		JLabel text1 = new JLabel("用户�?");
		JLabel text2 = new JLabel("密码");
		JButton button = new JButton("确认注册");
		JTextField nameinput = new JTextField(20);
		JPasswordField pwinput = new JPasswordField(20);
		photojp.add(box);
		namejp.add(text1);
		namejp.add(nameinput);
		passwdjp.add(text2);
		passwdjp.add(pwinput);
		btnjp.add(button);
		Regjf.add(photojp);
		Regjf.add(namejp);
		Regjf.add(passwdjp);
		Regjf.add(btnjp);
		Regjf.setVisible(true);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			if("确认注册".equals(e.getActionCommand())){
			
			System.out.println("zhuce");
			Regmsg regmsg = new Regmsg();
			regmsg.photoid=box.getSelectedIndex();
//			System.out.println(regmsg.photoid);
			regmsg.type_id=1;
			regmsg.user_name=nameinput.getText();
			regmsg.user_password=String.valueOf(pwinput.getPassword());
			qc.sendmsg(regmsg);
//			System.out.println(nameinput.getText()+"   "+String.valueOf(pwinput.getPassword()));
				}
			}
		});
	}
	static Chatmsg chatmsg = new Chatmsg();
	public static void Chatui(){

		JFrame chatjf = new JFrame();
		JPanel southjp = new JPanel();
		southjp.setLayout(new BorderLayout());
		JPanel northjp = new JPanel();
		JPanel btnjp = new JPanel();
		
		btnjp.setLayout(new BorderLayout());
//		BorderLayout f = (BorderLayout)btnjp.getLayout();
//		f.setHgap(20);
		

		chatjf.setDefaultCloseOperation(2);
		chatjf.setSize(700, 550);
		chatjf.setLocationRelativeTo(null);
		chatjf.setLayout(new BorderLayout());
		
		JTextArea log= new JTextArea(22, 62);
		log.setLineWrap(true);        //�?活自动换行功�? 
		log.setWrapStyleWord(true);
		log.setEditable(false);
		
		JScrollPane ScrollPane = new JScrollPane(log);
		ScrollPane.setHorizontalScrollBarPolicy(
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		ScrollPane.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		JTextArea input= new JTextArea(7, 62);
		
		input.setBackground(new Color(248 ,248 ,255));
		input.setLineWrap(true);        //�?活自动换行功�? 
		input.setWrapStyleWord(true);
//		text.setBackground(new Color(248 ,248 ,255));
		southjp.setBackground(new Color(248 ,248 ,255));
		northjp.setBackground(new Color(248 ,248 ,255));
		log.setBackground(new Color(248 ,248 ,255));
		btnjp.setBackground(new Color(248 ,248 ,255));
		JButton btn = new JButton("发�??");
		btnjp.add(btn,BorderLayout.EAST);
		
		northjp.add(ScrollPane);
		chatjf.add(northjp,BorderLayout.NORTH);
		southjp.add(input,BorderLayout.NORTH);
		southjp.add(btnjp,BorderLayout.SOUTH);
		chatjf.add(southjp,BorderLayout.SOUTH);
		chatjf.setVisible(true);
		
		qc.getlog(log);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				chatmsg.type_id=Constants.chatid;
				chatmsg.user_id=qqclient.id;
				chatmsg.user_name=qqclient.username;
				chatmsg.msg=input.getText();
//				System.out.println(chatmsg.rec_id+"   "+chatmsg.rec_name);
//				chatmsg.rec_id=
//				chatmsg.rec_name=
				qc.sendmsg(chatmsg);
				input.setText("");
			}
		});
		
	}
	static MysqlMethod sqlmethod = new MysqlMethod();
	static JPanel friendjp;
	static JPanel jtabP1;
	static boolean listmark=false;
	public static void userui(int id,int photoid,String name){
		JFrame userjf = new JFrame("QQ");
		userjf.setDefaultCloseOperation(3);
		userjf.setSize(320,680);
		userjf.setLocation(1530,140);
		JPanel upjp = new JPanel();
		JPanel midjp = new JPanel();
		JPanel bottomjp = new JPanel();
//		JPanel leftjp =  new JPanel();
		JPanel rightjp = new JPanel();
		
		//上部面板的结�?
//		sqlmethod.id = Integer.valueOf(logmsg.user_name);
//		sqlmethod.query2("userinfo");
		
		upjp.setLayout(new BorderLayout());
		upjp.setSize(320, 120);
		
		JLabel username = new JLabel(name);
		JLabel idlabel = new JLabel("("+id+")");
//		JLabel blank = new JLabel("    ");
		username.setFont(new Font("宋体", Font.PLAIN, 15));
		ImageIcon headphoto = new ImageIcon("C:\\Users\\guansheng\\Desktop\\coding-materials\\imitate QQ\\"+photoid+".jpg");
		Image image = headphoto.getImage().getScaledInstance(70,70,0);
		headphoto.setImage(image);
		JLabel photolabel = new JLabel(headphoto);
		
		//		rightjp.add(blank);
		rightjp.add(photolabel);
		rightjp.add(username);
		rightjp.add(idlabel);
		rightjp.setBackground(new Color(255,255,255));

		BorderLayout f = (BorderLayout)upjp.getLayout();
		f.setHgap(0);
		upjp.add(rightjp,BorderLayout.WEST);
//		upjp.add(rightjp,BorderLayout.CENTER);
//		upjp.add(rightjp,BorderLayout.CENTER);
		
		//中部面板结构
		JPanel jpMid,jpBotm,jtabP2,jtabP3,jpMidN,jpBotmC;
		JTabbedPane jtabP;
		jtabP = new JTabbedPane();
		jtabP1 = new JPanel();
		jtabP2 = new JPanel();
		jtabP3 = new JPanel();
		jtabP1.setLayout(new BorderLayout());
//		JLabel jl = new JLabel("56543545");
		
		jtabP1.setName("好友列表");
		jtabP2.setName("Q群列�?");
		jtabP3.setName("�?近联�?");	
		
//		friendshow();
		
//		GridLayout gg = (GridLayout)friendjp.getLayout();
//		gg.setHgap(0);
		jtabP1.setBackground(new Color(255,255,255));
		jtabP.setBackground(new Color(255,255,255));
		jtabP.add(jtabP1);
		jtabP.add(jtabP2);
		jtabP.add(jtabP3);
		
//		ui.start();
		
		//下部面板结构
		JButton searchbtn = new JButton("添加好友");
		JButton pwdbtn = new JButton("修改密码");
		bottomjp.add(searchbtn);
		bottomjp.add(pwdbtn);
		
		upjp.setBackground(new Color(255,255,255));
		bottomjp.setBackground(new Color(255,255,255));

		userjf.add(upjp,BorderLayout.NORTH);
		userjf.add(bottomjp,BorderLayout.SOUTH);
		userjf.add(jtabP,BorderLayout.CENTER);
		userjf.setVisible(true);
		
		searchbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				searchui();
			}
		});
		pwdbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pwdset();
			}
		});
	}
	static JPanel cenjp = new JPanel();
	public static void searchui(){
		JFrame jf = new JFrame(){
			public void paint(Graphics g) {
				super.paint(g);
				g.setColor(Color.GRAY);
				g.drawLine(0,100,600 ,100);
				}
		};
		jf.setDefaultCloseOperation(2);
		jf.setSize(600,480);
		jf.setLocationRelativeTo(null);
		
		JPanel upjp = new JPanel();
		JPanel cenjp = new JPanel();
		

		JTextField input = new JTextField(30);
		JButton searchbtn = new JButton("查找");
		
		
		Dimension dimension = new Dimension(400,30);
		Dimension dimension1 = new Dimension(50,30);
		
		input.setPreferredSize(dimension);
		searchbtn.setPreferredSize(dimension1);
		
		ImageIcon image = new ImageIcon("C:\\Users\\guansheng\\Desktop\\coding-materials\\imitate QQ\\search.jpg");
		image.setImage(image.getImage().getScaledInstance(60,30,0));
		searchbtn.setIcon(image);
		
		FlowLayout f=(FlowLayout)upjp.getLayout();
		f.setVgap(20);
		f.setHgap(0);
		
		upjp.setSize(600, 200);
		cenjp.setSize(600, 280);
		cenjp.setBackground(new Color(245,255,250));
//		cenjp.setLayout(new BorderLayout());
		upjp.setBackground(new Color(245,255,250));
		
		upjp.add(input);
//		upjp.add(label);
		upjp.add(searchbtn);
		
		input.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				String temp = input.getText();
				if(temp.equals("")) {
					input.setForeground(Color.GRAY);
					input.setText("输入搜索的qq号或昵称");
				}
			}
			
			@Override
			public void focusGained(FocusEvent e){
				// TODO Auto-generated method stub
				String temp = input.getText();
				if(temp.equals("输入搜索的qq号或昵称")) {
					input.setText("");
					input.setForeground(Color.BLACK);
					}
				}
		});
	
		searchbtn.addActionListener (new ActionListener() {
			
			 
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cenjp.removeAll();
				cenjp.repaint();
//				cenjp = new JPanel();
				int id = -1;
				try{
				id = Integer.parseInt(input.getText());
				}catch(NumberFormatException ee){
					
				}
				int photoid;
				String username;
				Connection conn = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				cenjp.setLayout(new GridLayout(3, 3, 10, 10));
				try {
					conn = Conn.getConnection();
					//预编译sql
					ps = conn.prepareStatement("select * from userinfo where id=?");
					ps.setInt(1, id);
					rs = ps.executeQuery();
			
//					rs = st.executeQuery("select * from userinfo where username='admin'");
//					rs = st.executeQuery("select * from userinfo order by username desc");
					while(rs.next()) {
						Addmsg add = new Addmsg();
						add.id = rs.getInt("id");
						add.username = rs.getString("username");
						add.photoid = rs.getInt("photoid");
//						 status = rs.getInt("status");
						 ImageIcon image = new ImageIcon("C:\\Users\\guansheng\\Desktop\\coding-materials\\imitate QQ\\"+add.photoid+".jpg");
						 image.setImage(image.getImage().getScaledInstance(40,40,0));
//						 JPanel jp = new JPanel();
						JPanel jp = new JPanel();
						 JPanel leftjp = new JPanel();
						 leftjp.setBackground(new Color(245,255,250));
							JLabel jb = new JLabel();
							JLabel jb1 = new JLabel();
							JLabel jb2= new JLabel();
							JButton reqbtn = new JButton("添加好友");
						 jb.setIcon(image);
						 jb1.setText(add.username);
						 jb2.setText("("+add.id+")");
						 leftjp.add(jb);
						 leftjp.add(jb1);
						 leftjp.add(jb2);
						 leftjp.add(reqbtn);
						
						 
						 cenjp.add(leftjp);
						 cenjp.revalidate();

//						System.out.println("id="+id+",username="+username+",photoid="+photoid);
						reqbtn.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
//								sqlmethod.id = add.id;
								sqlmethod.addinsert(qc.username+"_friends", add.id, add.username, add.photoid);
								sqlmethod.addinsert(add.username+"_friends",qc.id , qc.username, qc.photoid);
								JOptionPane.showMessageDialog(null, "添加成功");
//								friendshow();
//								jtabP1.validate();
//								jtabP1.repaint();
							}
						});
						
					}
					username = input.getText();
					ps = conn.prepareStatement("select * from userinfo where username=?");
					ps.setString(1, username);
					rs = ps.executeQuery();
					while(rs.next()) {
						Addmsg add = new Addmsg();
						add.id = rs.getInt("id");
						add.username = rs.getString("username");
						add.photoid = rs.getInt("photoid");
//						 status = rs.getInt("status");
						 ImageIcon image = new ImageIcon("C:\\Users\\guansheng\\Desktop\\coding-materials\\imitate QQ\\"+add.photoid+".jpg");
						 image.setImage(image.getImage().getScaledInstance(40,40,0));
//						 JPanel jp = new JPanel();
						 JPanel jp = new JPanel();
						 JPanel leftjp = new JPanel();
						 leftjp.setBackground(new Color(245,255,250));
							JLabel jb = new JLabel();
							JLabel jb1 = new JLabel();
							JLabel jb2= new JLabel();
							JButton reqbtn = new JButton("添加好友");
						 jb.setIcon(image);
						 jb1.setText(username);
						 jb2.setText("("+add.id+")");
						 leftjp.add(jb);
						 leftjp.add(jb1);
						 leftjp.add(jb2);
						 leftjp.add(reqbtn);
						
//						 leftjp.add(jp);
						 cenjp.add(leftjp);
						 cenjp.revalidate();
						reqbtn.addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
//									sqlmethod.id = add.id;
									sqlmethod.addinsert(sqlmethod.username+"_friends", add.id, add.username, add.photoid);
									sqlmethod.addinsert(username+"_friends",sqlmethod.id , sqlmethod.username, sqlmethod.photoid);
									JOptionPane.showMessageDialog(null, "添加成功");
//									friendshow();
								}
							});
						 
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					try {
						rs.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						ps.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
	
//		leftjp.add(reqbtn);
		
		jf.add(upjp,BorderLayout.NORTH);
		jf.add(cenjp,BorderLayout.CENTER);
		jf.setVisible(true);
		
	}
	public static void pwdset(){
		JFrame Regjf=new JFrame();
		Regjf.setSize(380, 250);
		Regjf.setLocationRelativeTo(null);
		Regjf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Regjf.setLayout(new FlowLayout());
		
		JPanel namejp =new JPanel();
		JPanel photojp = new JPanel();
		JPanel passwdjp = new JPanel();
		JPanel btnjp = new JPanel();
//		photojp.setSize(380, 40);
		
//		box.addItem(photo1);
//		box.addItem(photo2);
//		box.addItem(photo3);
		
		JLabel text1 = new JLabel("旧密�?");
		JLabel text2 = new JLabel("新密�?");
		JLabel text3 = new JLabel("确认密码");
		JButton button = new JButton("确认修改");
		JPasswordField nameinput = new JPasswordField(20);
		JPasswordField pwinput = new JPasswordField(20);
		JPasswordField pwdinput = new JPasswordField(20);
		
		namejp.add(text1);
		namejp.add(nameinput);
		passwdjp.add(text2);
		passwdjp.add(pwinput);
		photojp.add(text3);
		photojp.add(pwdinput);
		btnjp.add(button);
		Regjf.add(namejp);
		Regjf.add(passwdjp);
		Regjf.add(photojp);
		Regjf.add(btnjp);
		Regjf.setVisible(true);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
			Setpwdmsg spwdmsg = new Setpwdmsg();
			spwdmsg.new_pwd=String.valueOf(pwinput.getPassword());
			spwdmsg.check_pwd=String.valueOf(pwdinput.getPassword());
			if(spwdmsg.new_pwd.equals(spwdmsg.check_pwd)){
			spwdmsg.type_id=Constants.setpwdid;
			spwdmsg.user_id=qqclient.id;
			spwdmsg.old_pwd=String.valueOf(nameinput.getPassword());
			qc.sendmsg(spwdmsg);
			}
			else{
				JOptionPane.showMessageDialog(null, "密码输入不一�?");
			}
				}
			
		});
	}
	static int number = 0;
	public static void friendshow(DataInputStream dis){
//		jtabP1.removeAll();
		JLabel t = new JLabel("我的好友");
		JPanel p = new JPanel();
		
		p.setLayout(new BorderLayout());
		
		ImageIcon markimg1 = new ImageIcon("C:\\Users\\guansheng\\Desktop\\coding-materials\\imitate QQ\\listmark1.jpg");
		markimg1.setImage(markimg1.getImage().getScaledInstance(11,11,0));
		
		
		ImageIcon markimg2 = new ImageIcon("C:\\Users\\guansheng\\Desktop\\coding-materials\\imitate QQ\\listmark2.jpg");
		markimg2.setImage(markimg2.getImage().getScaledInstance(11,11,0));
//		JLabel imglab2 = new JLabel(markimg1);
		
		JLabel imglab = new JLabel();
		
		t.setFont(new Font("黑体", 0, 18));
		if(listmark){
//			System.out.println("226");
			imglab.setIcon(markimg2);
		}
		else{
			imglab.setIcon(markimg2);
		}
		p.add(imglab,BorderLayout.WEST);
		p.add(t);
		
		p.setBackground(new Color(255,255,255));
		friendjp = new JPanel();
		friendjp.setLayout(new BorderLayout());
		cenjp.setLayout(new GridLayout(10, 1,0,0));
		GridLayout gg = (GridLayout)cenjp.getLayout();
		gg.setHgap(0);
		BorderLayout g = (BorderLayout)p.getLayout();
		g.setHgap(10);
		friendjp.add(p,BorderLayout.NORTH);
		
		p.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				if(number==0){
					listmark=true;
					number++;
//					friendshow(qqclient.dis);
					imglab.setIcon(markimg1);
					friendjp.remove(cenjp);
					friendjp.revalidate();
					
				}
				else if(number==1){
					listmark=false;
					number=0;
//					friendshow(qqclient.dis);
					imglab.setIcon(markimg2);
					friendjp.add(cenjp);

					friendjp.revalidate();
				}
//				System.out.println(listmark);
			}
		});
		
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
		
		int count = 0;
		try {
			count = dis.readInt();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
//			conn = Conn.getConnection();
//			//预编译sql
//			ps = conn.prepareStatement("select * from "+sqlmethod.username+"_friends where mark=?");
//			ps.setInt(1, 0);
//			rs = ps.executeQuery();
			
//			rs = st.executeQuery("select * from userinfo where username='admin'");
//			rs = st.executeQuery("select * from userinfo order by username desc");
			for(int i=0;i<count;i++) {
//				Addmsg add = new Addmsg();
				int qqid = dis.readInt();
				int photoid = dis.readInt();
				byte[] usename = new byte[dis.readInt()];
				dis.readFully(usename);
				String name = new String(usename);
				JPanel jp = new JPanel();
				ImageIcon img = new ImageIcon("C:\\Users\\guansheng\\Desktop\\coding-materials\\imitate QQ\\"+photoid+".jpg");
				img.setImage(img.getImage().getScaledInstance(40,40,0));
				JLabel jb = new JLabel(img);
				JLabel jb1 = new JLabel(name);
				JLabel jb2= new JLabel("("+qqid+")");
				
				jp.setBackground(new Color(255,255,255));
				
//				if(listmark){
					jp.add(jb);
					jp.add(jb1);
					jp.add(jb2);
					cenjp.add(jp);
//					System.out.println(456);
//				}

//				else{
//					imglab.setIcon(markimg1);
//				}
					
//				friendjp.repaint();
//				friendjp.validate();
//				jtabP1.repaint();
				
				jp.addMouseListener(new MouseListener() {
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
					}
					int count = 0;
					long starttime;
					long endtime;
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						if(count==0){
						starttime = System.currentTimeMillis();
						}
						else if(count==1){
						endtime = System.currentTimeMillis();
						}
						count++;
						if(count==2){
							long time = endtime-starttime;
							if(time<1000){
								Chatui();
								chatmsg.rec_id=qqid;
								chatmsg.rec_name=name;
							}
							count=0;
						}
					}
				});
			}
			cenjp.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
//					System.out.println(e.getY());
				}
			});
			cenjp.setBackground(new Color(255,255,255));
			friendjp.setBackground(new Color(255,255,255));
			friendjp.add(cenjp);
//			System.out.println(111);
			jtabP1.setBackground(new Color(255,255,255));
			jtabP1.add(friendjp,BorderLayout.WEST);
			jtabP1.validate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
//			finally {
//			try {
//				rs.close();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			try {
//				ps.close();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			try {
//				conn.close();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
	}

	static MainUI ui = new MainUI();
	public static void main(String[] args){

		
		ui.mainui();
//		ui.pwdset();
//		ui.Registerui();
//		ui.searchui();
	}

}
