package com.the.saveloginpass

import android.app.ProgressDialog
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

class UpworkActivity : AppCompatActivity() {

    var upwork_webview: WebView? = null
    var layout_refresh: SwipeRefreshLayout? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upwork)

        supportActionBar!!.hide()

        layout_refresh = findViewById(R.id.swipe_refresh)
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading Data...")
        progressDialog.setCancelable(false)
        upwork_webview = findViewById(R.id.upwork_webview)
        upwork_webview!!.requestFocus()

        upwork_webview!!.getSettings().javaScriptEnabled = true




        upwork_webview!!.getSettings().databasePath =
            applicationContext.filesDir.absolutePath + "/databases"
        upwork_webview!!.loadUrl("https://www.upwork.com/")

        upwork_webview!!.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        })

        upwork_webview!!.setWebChromeClient(object : WebChromeClient() {
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
            upwork_webview!!.reload()
            layout_refresh!!.setRefreshing(false)
        })


    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    if (upwork_webview!!.canGoBack()) {
                        upwork_webview!!.goBack()
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