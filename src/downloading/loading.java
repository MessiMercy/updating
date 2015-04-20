package downloading;

import java.io.IOException;
import java.util.Stack;

public class loading {
	public static Stack<String> stack = new Stack<String>();
	public static int h = 0;

	public static void main(String[] args) throws IOException {
		// AcountRead readfromtxt = new AcountRead();
		// readfromtxt.readpack();
		// IsUrlAvailable isAvailable = new IsUrlAvailable();
		// boolean abc = isAvailable
		// .canConnect("https://play.google.com/store/apps/details?id=com.ea");
		// System.out.println(abc);
		// MarketSession session = new MarketSession(false);
		// session.login(LOGIN_STRING, PASSWORD, ANDROIDID);
		AcountRead acr = new AcountRead();
		stack = acr.readpack();
		h = stack.size();
		MultiThread threads = new MultiThread();
		Thread my0 = new Thread(threads);
		Thread my1 = new Thread(threads);
		Thread my2 = new Thread(threads);
		Thread my3 = new Thread(threads);
		Thread my4 = new Thread(threads);
		Thread my10 = new Thread(threads);
		Thread my11 = new Thread(threads);
		Thread my12 = new Thread(threads);
		Thread my13 = new Thread(threads);
		Thread my14 = new Thread(threads);
		my0.start();
		my1.start();
		my2.start();
		my3.start();
		my4.start();
		my10.start();
		my11.start();
		my12.start();
		my13.start();
		my14.start();

	}

}
// TODO 自动生成的方法存根

