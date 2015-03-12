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
					System.out.println("已查找到的个数： " + i);
					i++;
					Thread.sleep(500);
				} else {
					resultMap.put(tempString, "App Not Found");
					System.out.println("发现已下架应用的个数： " + j);
					j++;
					Thread.sleep(500);
				}
				System.out.println("目前进度： " + (i + j) + "/" + h);
				if ((i + j) % 100 == 0) {
					System.out.println("已阅100个，休息100000毫秒........");
					printRes.func();
					Thread.sleep(100000);
				}
			}

		} catch (IOException | InterruptedException e) {
			// TODO 自动生成的 catch 块
			System.out.println(e);
		}
		// TODO 自动生成的方法存根

	}
}
