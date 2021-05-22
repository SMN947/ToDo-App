package com.smn947.todofree;

import android.app.*;
import android.os.*;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import okhttp3.*;
import java.io.IOException;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		OkHttpClient client = new OkHttpClient();
		MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(mediaType, "uid=test1%2f26&cell=0");

		reqStart(client, body);
    }

	public void reqStart(OkHttpClient client, RequestBody body) {
		Request request = requestBuild(body);
		client.newCall(request).enqueue(new Callback() {
        	@Override
        	public void onFailure(Call call, IOException e) {
				e.printStackTrace();
			}
	
			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if (response.isSuccessful()) {
					final String myResponse = response.body().string();
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							System.out.print("LLEGO =====================");
							System.out.print(myResponse);
							/*String replace2 = (myResponse.replace("[[{", "[{")).replace("}],[", "}]CORTEN");
							String p = "";
							try {
								JSONArray X = new JSONArray(replace2.split("CORTEN")[0]);
								for (int i=0; i < X.length(); i++) {
									JSONObject u = X.getJSONObject(i);
									p += ((int)u.get("rank") + 1) + " | " + u.get("member") + " • " + u.get("score") + "\n";
								}
							}catch (JSONException e) {
								System.out.println("elerror---------");
								System.out.println(e.toString());
							}*/
						}
					});
				}
			}
        });
    }

	public Request requestBuild(RequestBody body)
	{
	Request request = new Request.Builder()
		.url("http://107.170.194.172:3704/checkadd")
		.post(body)
		.addHeader("Connection", "keep-alive")
		.addHeader("Pragma", "no-cache")
		.addHeader("Cache-Control", "no-cache")
		.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36")
		.addHeader("Content-Type", "application/x-www-form-urlencoded")
		.addHeader("Accept", "*/*")
		.addHeader("Origin", "http://107.170.194.172:3704")
		.addHeader("Referer", "http://107.170.194.172:3704/checkadd")
		.addHeader("Accept-Language", "en,gl;q=0.9,es;q=0.8,ja;q=0.7,ca;q=0.6")
		.build();
	return request;
	}
}
