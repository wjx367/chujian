package com.viewwang.chujian;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebActivity extends Activity {

    @Bind(R.id.pb)
    ProgressBar pb;
    @Bind(R.id.webview)
    WebView webview;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        WebSettings webSettings=webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setBuiltInZoomControls(true);
        webview.loadUrl("http://www.baidu.com");
        webview.setWebViewClient(new webViewClient());
        if (Build.VERSION.SDK_INT >= 19) {
            webview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        }
        webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged( WebView view, int newProgress) {

                if(newProgress==100){
//                  progressBar.setVisibility(view.INVISIBLE);
                    pb.setVisibility(view.GONE);
                }else
                {
                    if(view.INVISIBLE==pb.getVisibility()){
                        pb.setVisibility(view.VISIBLE);

                    }
                    pb.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        finish();
        return false;
    }
private class webViewClient extends WebViewClient{
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

}
}