package com.viewwang.chujian.util;

import android.content.Context;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyHttpClient {
	private static HttpClient myHttpClient;

	private MyHttpClient() {
	}
	
	public static synchronized HttpClient getHttpClient(Context context) {
		if (myHttpClient == null) {
			
			KeyStore trustStore;
			SSLSocketFactory socketFactory = null;
			try {
				trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
				trustStore.load(null, null);
				socketFactory = new SSLSocketFactoryEx(trustStore);
				socketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  //允许所有主机的验证
				Log.d("HttpClient", "Succeed To Initial SSL");
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				Log.d("HttpClient", "Failed To Initial SSL");
				e.printStackTrace();
			}
			
			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
			HttpProtocolParams.setUseExpectContinue(params, true);
			HttpProtocolParams.setUserAgent(params,
					"Mozilla/5.0 (Linux; U; Android 2.2.1; en-us; Nexus One Build/FRG83) AppleWebKit/533.(KHTML, like Gecko) Version/4.0 Mobile Safari/533.1"
			);
			
			 // 超时设置
            /* 从连接池中取连接的超时时间 */
			ConnManagerParams.setTimeout(params, 1000);
			 /* 连接超时 */
			HttpConnectionParams.setConnectionTimeout(params, 5000);
			 /* 请求超时 */
			HttpConnectionParams.setSoTimeout(params, 10000);

			// 设置HttpClient支持HTTP和HTTPS两种模式
			SchemeRegistry schReg = new SchemeRegistry();
			schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			if(socketFactory != null)
			{
				schReg.register(new Scheme("https", socketFactory, 443));
			}
			else
			{
				schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
			}
			 // 使用线程安全的连接管理来创建HttpClient
			ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params,schReg);
			myHttpClient = new DefaultHttpClient(conMgr, params);
		}
		return myHttpClient;
	}
	
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public static String get(HttpClient client, String url, String sessionId) {
		String result = "";
		 // 创建GET请求
		 HttpGet request = new HttpGet(url);
		try {

			// request.setHeader("Cookie",sessionId); 
			 // 发送请求     
			 HttpResponse response = client.execute(request);
			 if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					Log.d("MyHttpClient", "Request " + url + " success.");
					BufferedReader buff_reader = new BufferedReader(
							new InputStreamReader(response.getEntity().getContent()));
					String line = null;
					while ((line = buff_reader.readLine()) != null) {
						result += line;
					}
				} else {
					Log.e("MyHttpClient", "Unexpected status code: "
							+ response.getStatusLine().getStatusCode());
				}
		 }
		catch(HttpHostConnectException e)
		{
			Log.e("HttpClient", "net connected failed");
			result = "error_connect";
			e.printStackTrace();
		}
		catch(SocketTimeoutException e)
		{
			Log.e("HttpClient", "net connected failed");
			result = "error_connect";
			e.printStackTrace();
		}
		catch(UnknownHostException e)
		{
			Log.e("HttpClient", "net connected failed");
			result = "error_connect";
			e.printStackTrace();
		}
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		request.abort();
		return result;
	}

	public static String post(HttpClient client, String url, String sessionId, List<NameValuePair> params) {
		String result = "";
		try {
			HttpPost httpPost = new HttpPost(url);
			//httpPost.setHeader("Cookie", "JSESSIONID=" + sessionId);
			httpPost.setHeader("Cookie",  sessionId);
			HttpResponse response = null;
            // 设置httpPost请求参数 
            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            response = client.execute(httpPost); 
            //System.out.println(httpResponse.getStatusLine().getStatusCode()); 
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 使用getEntity方法获得返回结果 
                result = EntityUtils.toString(response.getEntity());
            } else {
				Log.e("MyHttpClient", "Unexpected status code: "
						+ response.getStatusLine().getStatusCode());
			}
		} 
		catch(UnknownHostException e)
		{
			Log.e("HttpClient", "net connected failed");
			result = "error_connect";
			e.printStackTrace();
		}
		catch(HttpHostConnectException e)
		{
			Log.e("HttpClient", "net connected failed");
			result = "error_connect";
			e.printStackTrace();
		}
		catch(Exception e)
		{
			Log.e("HttpClient", "catch exception");
			e.printStackTrace();
		}
		return result;
	}
	
	public static HttpResponse filepost(HttpClient client, String url, String sessionId) {
		try {
			HttpPost httpPost = new HttpPost(url);
			//httpPost.setHeader("Cookie", "JSESSIONID=" + sessionId);
			httpPost.setHeader("Cookie",  sessionId);
			HttpResponse response = null;
            // 设置httpPost请求参数 
            response = client.execute(httpPost); 
            //System.out.println(httpResponse.getStatusLine().getStatusCode()); 
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 使用getEntity方法获得返回结果 
               return response;  
            } else {
				Log.e("MyHttpClient", "Unexpected status code: "
						+ response.getStatusLine().getStatusCode());
			}
		} 
		catch(UnknownHostException e)
		{
			Log.e("HttpClient", "net connected failed");
			e.printStackTrace();
		}
		catch(HttpHostConnectException e)
		{
			Log.e("HttpClient", "net connected failed");
			e.printStackTrace();
		}
		catch(Exception e)
		{
			Log.e("HttpClient", "catch exception");
			e.printStackTrace();
		}
		return null;
	}
	public static byte[] getGifImg(HttpClient client, String url) {
		byte[] gif_bytes = null;
		try {
			 HttpGet request = new HttpGet(url);
			 //request.setHeader("Cookie","JSESSIONID="+sessionId);        
			 HttpResponse response = client.execute(request);
			 Log.d("HttpClient: status", ""+response.getStatusLine().getStatusCode());
			 if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					Log.d("MyHttpClient", "Request " + url + " success.");
					InputStream in = response.getEntity().getContent();
					gif_bytes = streamToBytes(in);
			 } else {
					Log.e("MyHttpClient", "Unexpected status code: "
							+ response.getStatusLine().getStatusCode());
				}
		 }
		catch(HttpHostConnectException e)
		{
			Log.e("HttpClient", "net connected failed");
			e.printStackTrace();
		}
		catch(UnknownHostException e)
		{
			Log.e("HttpClient", "net connected failed");
			e.printStackTrace();
		}
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		return gif_bytes;
	}
	
	public static Map<String, Object> getImg(HttpClient client, String url) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		HttpGet request = new HttpGet(url);
		try {     
			 HttpResponse response = client.execute(request);
			 Log.d("HttpClient: status", ""+response.getStatusLine().getStatusCode());
			 if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				 if(response.getEntity() != null)
				 {
					Log.d("MyHttpClient", "Request " + url + " success.");
					InputStream in = response.getEntity().getContent();
					retMap.put("code", 0);
					retMap.put("in", in);
				 }
				 else
				 {
					 retMap.put("code", -4);
					 Log.e("MyHttpClient", "entity is null");
				 }
			 } else {
				retMap.put("code", -1);
				Log.e("MyHttpClient", "Unexpected status code: "+ response.getStatusLine().getStatusCode());
			 }
		 }
		catch(HttpHostConnectException e)
		{
			retMap.put("code", -2);
			Log.e("HttpClient", "net connected failed");
			e.printStackTrace();
		}
		catch(UnknownHostException e)
		{
			retMap.put("code", -2);
			Log.e("HttpClient", "net connected failed");
			e.printStackTrace();
		}
		 catch(Exception e)
		 {
			 retMap.put("code", -3);
			 e.printStackTrace();
		 }
		request.abort();
		return retMap;
	}
	
    private static byte[] streamToBytes(InputStream is) {
        ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = is.read(buffer)) >= 0) {
                os.write(buffer, 0, len);
            }
        } catch (java.io.IOException e) {
        	e.printStackTrace();
        }
        Log.d("HttpClient", Tool.getNowTime()+ " WebGifView:Succeed To Convert Stream to Bytes");
        return os.toByteArray();
    }
}