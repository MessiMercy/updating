package downloading;

import java.util.HashMap;

public class MultiThread implements Runnable {
	public static HashMap<String, String> resultMap = new HashMap<String, String>();
	public static int i = 0;// �Ѳ��Ҹ���
	public static int j = 0;// δ�ҵ�����

	@Override
	public void run() {// ����д
		search se = new search();

		PrintResult printRes = new PrintResult();
		// IsUrlAvailable avi = new IsUrlAvailable();
		// int i = 0;
		// int j = 0;
		try {
			// Stack<String> stack = acr.readpack();

			while (!loading.stack.empty()) {
				String tempString = loading.stack.pop();
				resultMap.put(tempString, se.fun(tempString));
				System.out.println("�Ѳ��ҵ��ĸ����� " + i);
				Thread.sleep(1000);
				i++;
				if (resultMap.get(tempString) == "app offline") {
					System.out.println("���¼ܸ����� " + j);
					j++;

				}

				System.out.println("Ŀǰ���ȣ� " + (i + j) + "/" + loading.h);
				if ((i + j) % 100 == 0) {
					System.out.println("����100������Ϣ100000����........");
					printRes.func();
					Thread.sleep(100000);
				}
				if ((i + j) >= loading.h) {
					System.out.println("���������");
					printRes.func();
					System.exit(0);
				}
			}

		} catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
			System.out.println(e);
		}
		// TODO �Զ����ɵķ������

	}
}
