package ImitateQQ;

import java.io.DataInputStream;

public abstract class Message {
	int type_id;
	int user_id;
	String user_name;
	String user_password;
	int photoid;
	int rec_id;
	String rec_name;
	String msg;
	String old_pwd;
	String new_pwd;
	String check_pwd;
	public abstract byte[] pack();
}
