package ImitateQQ;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Setpwdmsg extends Message{
	public byte[] pack(){
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		try {
//			System.out.println(type_id);
			dos.writeInt(type_id);
			dos.writeInt(user_id);
			byte[] namebyte = old_pwd.getBytes();
			byte[] pwdbyte = new_pwd.getBytes();
			byte[] checkpwd = check_pwd.getBytes();
			dos.writeInt(namebyte.length);
			dos.write(namebyte);
			dos.writeInt(pwdbyte.length);
			dos.write(pwdbyte);
			dos.writeInt(checkpwd.length);
			dos.write(checkpwd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bos.toByteArray();
	}

}
