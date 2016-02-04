package com.viewwang.chujian.util;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.viewwang.chujian.G;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class CommonRequest {
	private Context context;

	// 绑定银行卡
	public void bindcard(Context context) {
		this.context = context;
		new BankBoundTask().execute();
	}

	private class BankBoundTask extends AsyncTask<Void, Void, String> {
		@Override
		protected String doInBackground(Void... arg) {
			// Simulates a background job.
			String result = "";
			try {
				String url = G.URL_USER_BANKCARD;
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("appSource", "android"));
				params.add(new BasicNameValuePair("appVersion", G.APPVERSION));
				if (result.equals("error_connect")) {
					Log.d("SelfCenterFragment", "New Work Conneted Failed");
					result = "error_connect";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("SelfCenterFragment", "Failed To Get User Info");
				result = "error";
			}
			return result;
		}


	}

}
