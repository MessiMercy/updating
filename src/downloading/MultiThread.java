package downloading;

import java.util.HashMap;

public class MultiThread implements Runnable {
	public static HashMap<String, String> resultMap = new HashMap<String, String>();
	public static int i = 0;// 已查找个数
	public static int j = 0;// 未找到个数

	@Override
	public void run() {// 待重写
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
				System.out.println("已查找到的个数： " + i);
				Thread.sleep(1000);
				i++;
				if (resultMap.get(tempString) == "app offline") {
					System.out.println("已下架个数： " + j);
					j++;

				}

				System.out.println("目前进度： " + (i + j) + "/" + loading.h);
				if ((i + j) % 100 == 0) {
					System.out.println("已阅100个，休息100000毫秒........");
					printRes.func();
					Thread.sleep(100000);
				}
				if ((i + j) >= loading.h) {
					System.out.println("任务已完成");
					printRes.func();
					System.exit(0);
				}
			}

		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			System.out.println(e);
		}
		// TODO 自动生成的方法存根

	}
}
