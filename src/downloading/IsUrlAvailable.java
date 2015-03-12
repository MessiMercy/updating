package downloading;

import java.net.HttpURLConnection;
import java.net.URL;

public class IsUrlAvailable {
	// private static final String ECODING = "UTF-8";

	public boolean canConnect(String urlStr) {
		// public boolean isValid(String strLink) {
		// return false;
		URL uri;
		try {
			uri = new URL(urlStr);
			HttpURLConnection connt = (HttpURLConnection) uri.openConnection();
			connt.setRequestMethod("HEAD");
			String str = connt.getResponseMessage();
			if (str.compareTo("Not Found") == 0) {
				return false;
			}
			connt.disconnect();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			System.out.println(e);
			return false;
		}
		return true;

	}

}
