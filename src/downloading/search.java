package downloading;

import java.util.HashMap;
import java.util.List;

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
	public static boolean single = true;

	// String finalpackage = null;
	HashMap<String, String> pacandversion = new HashMap<String, String>();

	String fun1(String asset) {// 在无法挂代理下的测试方法
		pacandversion.put(asset, (String) (asset.length() + ""));
		return (String) (asset.length() + "");

	}

	@SuppressWarnings("finally")
	// fun方法实现传入一个包名并得到其版本号
	String fun(String assetId) {

		MarketSession session = new MarketSession(false);
		session.login(LOGIN_STRING, PASSWORD, ANDROIDID);
		// if (single) {
		// System.out.println("login..");
		// session.login(LOGIN_STRING, PASSWORD, ANDROIDID);
		// System.out.println("login success");
		// single = false;
		// }
		// System.out.println("Login...");
		String query = assetId;
		// session.set
		AppsRequest appsRequest = AppsRequest.newBuilder().setQuery(query)
				.setStartIndex(0).setEntriesCount(10)
				.setWithExtendedInfo(false).build();

		Callback<AppsResponse> callback = new Callback<AppsResponse>() {

			public void onResult(ResponseContext context, AppsResponse response) {
				List<App> list = response.getAppList();

				for (int i = 0; i < list.size(); i++) {
					pacandversion.put(list.get(i).getPackageName(), list.get(i)
							.getVersion());
				}
			}
		};
		// 还差分别得到每一个包名的查询结果并遍历比对得到正确的包名，再得到version。
		// 最后输出至原文件内。
		try {
			session.append(appsRequest, callback);

			session.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("api cannot find it");
		} finally {
			// return "没有查找到";
			if (pacandversion.containsKey(assetId)) {
				return pacandversion.get(assetId);
			} else {
				System.out.println("并没有找到合适的");
				return "can`t get answer";
			}

		}

	}

}
