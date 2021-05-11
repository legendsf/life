package test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.BeanSerializer;

public class KryoTest {

	public static void main(String[] args) {
		User user = new User();
		user.setUserage(20);
		user.setUsername("zhangsan"); //
		//javaSerial(user, "e:/user.dat");
		//kryoSerial(user, "e:/user1.dat");
		User user1 = kryoDeSerial(User.class, "e:/user1.dat");
		System.out.println(user1.getUsername());
		System.out.println(user1.getUserage());
	}
    
	public static void javaSerial(Serializable s, String filepath) {
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filepath)));
			out.writeObject(s);
			out.flush(); 
			out.close();  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static <T> T kryoDeSerial(Class<T> c, String filepath) {
		try {
			Kryo kryo=new Kryo();  
			kryo.register(c,new BeanSerializer(kryo, c));  
	        Input input = new Input(new BufferedInputStream(new FileInputStream(filepath)));        
	        T t = kryo.readObject(input, c);
	        input.close();  
	        return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void kryoSerial(Serializable s, String filepath) {
		
		try {
			Kryo kryo=new Kryo();  
			kryo.register(s.getClass(),new BeanSerializer(kryo, s.getClass()));  
	        Output output=new Output(new BufferedOutputStream(new FileOutputStream(filepath)));        
	        kryo.writeObject(output, s);  
	        output.flush(); 
	        output.close();  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class User implements Serializable {  
 
	private transient String username;
	private int userage;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getUserage() {
		return userage;
	}
	public void setUserage(int userage) {
		this.userage = userage;
	}
	
}