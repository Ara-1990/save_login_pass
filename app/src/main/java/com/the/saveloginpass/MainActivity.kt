package com.the.saveloginpass

import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener

class MainActivity : AppCompatActivity() {

    var webview: WebView? = null
    var layout_refresh: SwipeRefreshLayout? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()
        layout_refresh = findViewById(R.id.swipe_refresh)
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("data loading...")
        progressDialog.setCancelable(false)
        webview = findViewById(R.id.web_wiew)
        webview!!.requestFocus()

        webview!!.getSettings().javaScriptEnabled = true




        webview!!.getSettings().databasePath = applicationContext.filesDir.absolutePath + "/databases"

        webview!!.loadUrl("https://gmail.com")

        webview!!.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        })

        webview!!.setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                if (progress < 100) {
                    progressDialog.show()
                }
                if (progress == 100) {
                    progressDialog.dismiss()
                }
            }
        })

        layout_refresh!!.setOnRefreshListener(OnRefreshListener {
            layout_refresh!!.setRefreshing(true)
            webview!!.reload()
            layout_refresh!!.setRefreshing(false)
        })


    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    if (webview!!.canGoBack()) {
                        webview!!.goBack()
                    } else {
                        finish()
                    }
                    return true
                }
            }
        }
        return super.onKeyDown(keyCode, event)
    }

}