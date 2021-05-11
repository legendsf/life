package test;
import java.util.*;
public class TestTree1 {

	public static void main(String[] args) {
		
		Map<Emp, Emp> empMap = new HashMap<Emp, Emp>();
		
		for ( int i = 1; i <= 12; i++ ) {
			Emp emp = new Emp();
			empMap.put(emp, emp);
			System.out.println("xxx");
		}
		
	}

}
class Emp {

	@Override
	public int hashCode() {
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		return false;
	}
	
}