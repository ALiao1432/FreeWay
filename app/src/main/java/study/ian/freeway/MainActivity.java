package study.ian.freeway;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d(TAG, "onPageFinished: ");
                view.loadUrl("javascript:window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
            }
        });
        webView.loadUrl("https://1968.freeway.gov.tw/n_notify");
    }

    class MyJavaScriptInterface {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processHTML(String html) {
            showLongLog(html);
        }
    }

    private void showLongLog(String log) {
        int MAX_LOG_SIZE = 2000;

        String TAG = "SpeedView";
        if (log.length() > MAX_LOG_SIZE) {
            Log.d(TAG, "Display Log : " + log.substring(0, MAX_LOG_SIZE));
            showLongLog(log.substring(MAX_LOG_SIZE));
        } else {
            Log.d(TAG, "Display Log : " + log);
        }
    }
}
