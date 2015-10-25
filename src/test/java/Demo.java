import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Demo {
	
	public static void main(String[] args) {
		final BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
		//		            Bien name
		
		String name = "";
		String name1 = "";
		String hanhdong;
		
		try {
			System.out.println("Please Enter Your Name: ");
			name = dataIn.readLine();
			System.out.println("Please Enter Your Other Name: ");
			name1 = dataIn.readLine();
			System.out.println("Nhap hanh dong: (+, -, *, /,%) ");
			hanhdong = dataIn.readLine();
			System.out.println(hanhdong);
			if ("+".equals(hanhdong)) {
				System.out.println("Tong 2 chuoi la: " + name + name1);
			}
		}
		catch (final IOException e) {
			System.out.println("Error!");
		}
	}
}
