package ImitateQQ;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Chatmsg extends Message{
	public byte[] pack(){
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		try {
			dos.writeInt(type_id);
			dos.writeInt(user_id);
			byte[] namebyte = user_name.getBytes();
			byte[] pwdbyte = msg.getBytes();
			dos.writeInt(namebyte.length);
			dos.write(namebyte);
			dos.writeInt(rec_id);
			dos.writeInt(pwdbyte.length);
			dos.write(pwdbyte);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bos.toByteArray();
	}
}
