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

class InstagramActivity : AppCompatActivity() {

    var insta_webwiew: WebView? = null
    var layout_refresh: SwipeRefreshLayout? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instagram)


        supportActionBar!!.hide()

        layout_refresh = findViewById(R.id.swipe_refresh)
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("data loading")
        progressDialog.setCancelable(false)
        insta_webwiew = findViewById(R.id.insta_webwiew)
        insta_webwiew!!.requestFocus()

        insta_webwiew!!.getSettings().javaScriptEnabled = true



        insta_webwiew!!.settings.databasePath = applicationContext.filesDir.absolutePath + "/databases"
        insta_webwiew!!.loadUrl("https://www.instagram.com/")

        insta_webwiew!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }


        insta_webwiew!!.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                if (progress < 100) {
                    progressDialog.show()
                }
                if (progress == 100) {
                    progressDialog.dismiss()
                }
            }
        }

        layout_refresh!!.setOnRefreshListener(OnRefreshListener {
            layout_refresh!!.setRefreshing(true)
            insta_webwiew!!.reload()
            layout_refresh!!.setRefreshing(false)
        })


    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    if (insta_webwiew!!.canGoBack()) {
                        insta_webwiew!!.goBack()
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