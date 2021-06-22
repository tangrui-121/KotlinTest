package com.person.kotlintest.sign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.person.kotlintest.R
import com.person.kotlintest.log
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class SignMainActivity : AppCompatActivity(), CoroutineScope by MainScope()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        et_url.setText("https://www.baidu.com")
        bt_load.setOnClickListener {
            launch {
                tv_content.text = "开始加载请求..."
                tv_content.text = "加载完毕，网页内容如下：\n\n\n ${loadUrl(et_url.text.toString())}"
            }
        }
    }

    private suspend fun loadUrl(url: String) = withContext(Dispatchers.IO) {
        "loadUrl loadUrl loadUrl".log()
        var content = ""
        (URL(url).openConnection() as HttpURLConnection).apply {
            requestMethod = "GET"
            content = dealResponse(inputStream)
            disconnect()
        }
        return@withContext content
    }

    private fun dealResponse(inputStream: InputStream): String {
        val reader = BufferedReader(InputStreamReader(inputStream))
        return StringBuffer().apply {
            var str = reader.readLine()
            while (null != str) {
                append(str)
                str = reader.readLine()
            }
        }.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}
