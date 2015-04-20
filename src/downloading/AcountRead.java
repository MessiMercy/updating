package downloading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class AcountRead {
	public Stack<String> readpack() throws IOException {
		File sourceFile = new File("test.txt");
		FileReader frFileReader = new FileReader(sourceFile);
		BufferedReader bfrBufferedReader = new BufferedReader(frFileReader);
		String buf = null;
		Stack<String> mystack = new Stack<String>();
		while ((buf = bfrBufferedReader.readLine()) != null) {
			mystack.push(buf.split("	")[0]);
		}
		frFileReader.close();
		return mystack;// 此处得出所有包名压入栈内。

	}
}
