package downloading;

import java.io.File;
import java.io.RandomAccessFile;

public class PrintResult {
	void func() {
		try {

			File file = new File("test.txt");
			// BufferedWriter by=new BufferedWriter(new FileWriter(file));
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			String abcg = null;
			StringBuilder sbBuilder = new StringBuilder();
			while ((abcg = randomAccessFile.readLine()) != null) {
				if (MultiThread.resultMap.containsKey(abcg.split("	")[0])
						&& abcg.split("	").length <= 1) {
					sbBuilder.append(abcg + "	"
							+ MultiThread.resultMap.get(abcg.split("	")[0])
							+ "\r\n");

				} else {

					sbBuilder.append(abcg + "\r\n");

				}
			}
			randomAccessFile.seek(0);
			String res = new String(
					sbBuilder.toString().getBytes("iso-8859-1"), "utf-8");
			randomAccessFile.write(res.getBytes("utf-8"));
			randomAccessFile.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
