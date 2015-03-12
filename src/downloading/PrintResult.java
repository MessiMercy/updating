package downloading;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class PrintResult {
	void func() throws IOException {
		File file = new File("test.txt");
		// BufferedWriter by=new BufferedWriter(new FileWriter(file));
		RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
		String abcg = null;
		// String errorcode = new String("没有找到相应的包名".getBytes("iso-8859-1"),
		// "utf-8");
		StringBuilder sbBuilder = new StringBuilder();
		while ((abcg = randomAccessFile.readLine()) != null) {

			if (MultiThread.resultMap.containsKey(abcg.split("	")[5])
					&& abcg.split("	").length <= 6) {
				sbBuilder.append(abcg + "	"
						+ MultiThread.resultMap.get(abcg.split("	")[5])
						+ "\r\n");

			} else {

				sbBuilder.append(abcg + "\r\n");

			}
			// sbBuilder.append(abcg + "	"
			// + MultiThread.resultMap.get(abcg.split("	")[5]) + "\r\n");
		}
		randomAccessFile.seek(0);
		String res = new String(sbBuilder.toString().getBytes("iso-8859-1"),
				"utf-8");
		randomAccessFile.write(res.getBytes("utf-8"));
		randomAccessFile.close();

	}

}
