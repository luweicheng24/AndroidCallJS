package com.gsww.www.androidcalljs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login;
    private EditText name;
    private EditText psw;
    private WebView webView;
    private String user;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
          initView();



        webView = (WebView) findViewById(R.id.wb);
        webView.loadUrl("file:///android_asset/demo01.html");
        webView.setWebChromeClient(new WebChromeClient());
        webView.addJavascriptInterface(new AndroidInterface(), "android");
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setDefaultFontSize(20);



    }

    private void initView() {
        login = (Button) findViewById(R.id.but_login);
        login.setOnClickListener(this);
        name= (EditText) findViewById(R.id.et_name);
        psw= (EditText) findViewById(R.id.et_psw);

    }

    @Override
    public void onClick(View v) {
        user = name.getText().toString().trim();
        password = psw.getText().toString().trim();
        String login = "javascript:showUser('"+user+"','"+password+"')";
        Log.e("q", "onClick: "+login );
        webView.loadUrl(login);
    }
     class AndroidInterface{
         public AndroidInterface(){

         }
          @JavascriptInterface
         public void show(String msg){
             Toast.makeText(MainActivity.this, "Html传过来数据了"+msg, Toast.LENGTH_SHORT).show();
         }
     }
}
