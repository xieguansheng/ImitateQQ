package ImitateQQ;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class LoginMsg extends Message{

	@Override
	public byte[] pack() {
		// TODO Auto-generated method stub
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		try {
//			System.out.println(type_id);
			dos.writeInt(type_id);
			byte[] namebyte = user_name.getBytes();
			byte[] pwdbyte = user_password.getBytes();
			dos.writeInt(16+namebyte.length+pwdbyte.length);
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
