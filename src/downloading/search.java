package downloading;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gc.android.market.api.MarketSession;
import com.gc.android.market.api.MarketSession.Callback;
import com.gc.android.market.api.model.Market.App;
import com.gc.android.market.api.model.Market.AppsRequest;
import com.gc.android.market.api.model.Market.AppsResponse;
import com.gc.android.market.api.model.Market.ResponseContext;

public class search {
	public static final String LOGIN_STRING = "liu.lingtong.tianfu@gmail.com";
	public static final String PASSWORD = "liu920923";
	public static final String ANDROIDID = "321a3daba8e8c188";
	public static final String ECODING = "utf-8";
	// String finalpackage = null;
	HashMap<String, String> pacandversion = new HashMap<String, String>();

	String fun1(String asset) {
		if (asset.equals("abc")) {
			return null;
		}// ���޷��Ҵ����µĲ��Է���
		else {
			pacandversion.put(asset, (String) (asset.length() + " "));
			return (String) (asset.length() + " ");
		}

	}

	static String getHTML1(String url) {
		return url.length() + " ";
	}

	public static String article(String url) {
		Document doc;
		try {
			URL uri = new URL("https://play.google.com/store/apps/details?id="
					+ url + "&hl=en");
			HttpsURLConnection connection = (HttpsURLConnection) uri
					.openConnection();
			// connection.setRequestMethod("HEAD");
			connection.setRequestMethod("GET");
			String str = connection.getResponseMessage();
			if (str.compareTo("Not Found") == 0) {
				return "app offline";
			}
			connection.disconnect();
			// c
			doc = Jsoup.connect(
					"https://play.google.com/store/apps/details?id=" + url
							+ "&hl=en").get();
			Elements ListDiv = doc.getElementsByAttributeValue("class",
					"content");
			for (Element element : ListDiv) {
				Elements links = element.getElementsByAttributeValue(
						"itemprop", "softwareVersion");
				for (Element link : links) {
					String linkText = link.text().trim();
					return (linkText);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "aries";
		}
		return "aries";

	}

	static String getHTML(String url) {
		try {
			URL uri = new URL("https://play.google.com/store/apps/details?id="
					+ url + "&hl=en");
			HttpsURLConnection connection = (HttpsURLConnection) uri
					.openConnection();
			// connection.setRequestMethod("HEAD");
			connection.setRequestMethod("GET");
			String str = connection.getResponseMessage();
			if (str.compareTo("Not Found") == 0) {
				return "app offline";
			}
			// connection.setDoInput(true);
			InputStream in = connection.getInputStream();
			byte[] buf = new byte[10];
			StringBuffer sb = new StringBuffer();
			while ((in.read(buf, 0, buf.length)) > 0) {
				sb.append(new String(buf, ECODING));
			}
			in.close();
			connection.disconnect();
			if (sb.toString().contains("softwareVersion")) {
				String aaString = sb.substring(
						sb.indexOf("softwareVersion") + 18,
						sb.indexOf("Requires Android") - 59);
				if (aaString.length() >= 20) {
					return aaString.substring(0, 10);
				}
				return aaString;

			} else {
				return "aries";
			}
			// String Webcode = sb.toString();
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			System.out.println(url + "�Ҳ���");
			return "aries";
		}
	}

	// fun����ʵ�ִ���һ���������õ���汾��
	// ����һ���ж���ʹ�����ȴ�url��ʽ��ȡ�汾��
	String fun(String assetId) {
		try {
			String result = article(assetId);
			if (result != null && (!result.contains("aries"))) {
				return result;
			} else {

				MarketSession session = new MarketSession(false);
				System.out.println("Login...");
				String query = assetId;
				session.login(LOGIN_STRING, PASSWORD, ANDROIDID);
				AppsRequest appsRequest = AppsRequest.newBuilder()
						.setQuery(query).setStartIndex(0).setEntriesCount(10)
						.setWithExtendedInfo(false).build();

				Callback<AppsResponse> callback = new Callback<AppsResponse>() {

					public void onResult(ResponseContext context,
							AppsResponse response) {
						List<App> list = response.getAppList();

						for (int i = 0; i < list.size(); i++) {
							pacandversion.put(list.get(i).getPackageName(),
									list.get(i).getVersion());
						}
					}
				};
				// ����ֱ�õ�ÿһ�������Ĳ�ѯ����������ȶԵõ���ȷ�İ������ٵõ�version��
				// ��������ԭ�ļ��ڡ�
				session.append(appsRequest, callback);
				session.flush();
			}
		} catch (Exception e1) {
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
			return "error2";
		}
		if (pacandversion.containsKey(assetId)) {
			return pacandversion.get(assetId);
		} else {
			System.out.println("��û���ҵ����ʵ�");
			return "index 0";
		}
	}
}
