package downloading;

import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

public class MultiThread implements Runnable {
	public static HashMap<String, String> resultMap = new HashMap<String, String>();

	@Override
	public void run() {
		search se = new search();
		AcountRead acr = new AcountRead();
		PrintResult printRes = new PrintResult();
		IsUrlAvailable avi = new IsUrlAvailable();
		int i = 0;
		int j = 0;
		try {
			Stack<String> stack = acr.readpack();
			int h = stack.size();
			while (!stack.empty()) {
				String tempString = stack.pop();
				if (avi.canConnect("https://play.google.com/store/apps/details?id="
						+ tempString)) {
					resultMap.put(tempString, se.fun(tempString));
					System.out.println("�Ѳ��ҵ��ĸ����� " + i);
					i++;
					Thread.sleep(500);
				} else {
					resultMap.put(tempString, "App Not Found");
					System.out.println("�������¼�Ӧ�õĸ����� " + j);
					j++;
					Thread.sleep(500);
				}
				System.out.println("Ŀǰ���ȣ� " + (i + j) + "/" + h);
				if ((i + j) % 100 == 0) {
					System.out.println("����100������Ϣ100000����........");
					printRes.func();
					Thread.sleep(100000);
				}
			}

		} catch (IOException | InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
			System.out.println(e);
		}
		// TODO �Զ����ɵķ������

	}
}
