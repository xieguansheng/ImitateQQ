package ImitateQQ;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Regmsg extends Message{
	
//	int user_name_len;
	
//	int user_password_len;

	public byte[] pack(){
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		try {
//			System.out.println(type_id);
			dos.writeInt(type_id);
			dos.writeInt(photoid);
			byte[] namebyte = user_name.getBytes();
			byte[] pwdbyte = user_password.getBytes();
			dos.writeInt(20+namebyte.length+pwdbyte.length);
			dos.writeInt(namebyte.length);
			dos.write(namebyte);
			
			dos.writeInt(pwdbyte.length);
			dos.write(pwdbyte);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bos.toByteArray();
	}


}
