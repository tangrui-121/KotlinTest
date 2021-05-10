package com.person.kotlintest.download

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.DisplayMetrics
import android.view.*
import android.widget.TextView
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import com.person.kotlintest.*
import com.person.kotlintest.homedialog.DialogBuilder
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.security.SecureRandom
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager


/**
 * Created by Aaron PAN on 2020/5/19.
 * Description:
 */
class UpdateDialog : DialogFragment() {

    private val updateUrl =
        "http://fezz.ceshi.che300.com/che300pro/app/edj_1.7.1_fortest.zip"

//            private val updateUrl =
//        "https://downloads.che300.com/app/pro/dingjiasystem2.7.6.0_major.apk"

//    private val updateUrl =
//        "https://114.67.85.186:443/version_file/dev_version_file/2021_04_25_13_36_28/vpnclient_1.0.0.1_upgrade.zip"

    private val DOWNLOADING = 0x01
    private val DOWNLOAD_FINISH = 0x02
    private val DOWNLOAD_FAILURE = 0x03

    private lateinit var tv_update: TextView
    private lateinit var tv_title: TextView
    private lateinit var tv_msg: TextView
    private lateinit var v_failure: View
    private lateinit var v_close: View
    private lateinit var progress_bar: RoundProgressBar

    private var SAVE_PATH = ""
    private var cancelUpdate = false

    private var callback: UpdateDialogCallback? = null

    companion object {
        fun newInstance(): UpdateDialog {
            val fragment = UpdateDialog()
            return fragment
        }
    }

    override fun onStart() {
        super.onStart()
        val dw = dialog?.window
        dw!!.setBackgroundDrawableResource(android.R.color.transparent)
        val dm = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(dm)
        val params = dw.attributes
        params.gravity = Gravity.CENTER
//        params.width = (dm.widthPixels * 0.75).toInt()
        params.width = WindowManager.LayoutParams.WRAP_CONTENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        dw.attributes = params
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            SAVE_PATH = "${getDiskCacheDir(it)}/download"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = inflater.inflate(R.layout.pop_update_layout, null)
        v_close = view.findViewById<View>(R.id.close)
        v_close.singleClick {
            callback?.onUpdateCanceled()
            dismissAllowingStateLoss()
        }

        tv_update = view.findViewById<TextView>(R.id.button)
        tv_title = view.findViewById<TextView>(R.id.title)
        tv_msg = view.findViewById<TextView>(R.id.text)
        v_failure = view.findViewById<View>(R.id.update_failure)
        progress_bar = view.findViewById<RoundProgressBar>(R.id.update_progress)


        tv_title.text = "更新内容"
        tv_update.text = "立即更新"
        tv_msg.text = "赶紧给劳资更新"
        tv_update.singleClick {
            v_close.gone()
            progress_bar.visible()
            tv_msg.gone()
            tv_title.text = "正在下载"
            askCancelUpdate()
            downloadAPK()
        }
        return view
    }

    private fun dealWithZip() {
        var file = File(SAVE_PATH, FileUtils.getFileNameFromUrl(updateUrl))
        if (FileUtils.getFileNameFromUrl(updateUrl).endsWith("zip")) {
            val zipPath: String? = file.getAbsolutePath()
            var end: String = FileUtils.getFileNameFromUrl(updateUrl)
            end = end.substring(0, end.length - 4)
            activity?.let {
                val outPath: String = "${getDiskCacheDir(it)}/download" + "/" + end
//                val apkPath = "$outPath/$end.apk"
//                if (!File(apkPath).exists())
                FileUtils.unzipAPK(it, zipPath, outPath)
                if (File(outPath).exists()) {
                    val files: Array<File> = File(outPath).listFiles()
                    if (null != files) {
                        for (i in files.indices) {
                            "files[i].name = ${files[i].name}".log()
                            if (files[i].name.endsWith("apk")) {
                                file = files[i]
                            }
                        }
                    }
                }
//                else{
//                    "333333333333333333333333333333333333333".log()
//                    file = File(apkPath)
//                }
            }
        }
        context?.let { ctx ->
            installApp(ctx, file)
        }
    }

    fun installApp(context: Context, apkFile: File) {
        val uri: Uri
        val intent = Intent()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(
                context,
                BuildConfig.APPLICATION_ID + ".fileProvider", //调用获取当前主app的application的包名为前缀的provider
                apkFile
            )
        } else {
            uri = Uri.fromFile(apkFile)
        }
        intent.action = Intent.ACTION_VIEW
        intent.setDataAndType(uri, "application/vnd.android.package-archive")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        context.startActivity(intent)
    }

    private fun downloadAPK() {
        DownloadThread().start()
    }

    private fun askCancelUpdate() {
        tv_update.text = "取消更新"
        tv_update.singleClick {
            DialogBuilder(context)
                .cancelTextColor(R.color.blue_2a8cff)
                .sureText("确定")
                .cancelText("取消")
                .message("您将会在下次请求时收到更新提醒")
                .title("确定放弃更新么？")
                .onSureClickListener {
                    cancelUpdate = true
                    dismissAllowingStateLoss()
                    callback?.onUpdateCanceled()
                }
                .builder()
                .show()
        }
    }

    inner class DownloadThread : Thread() {
        override fun run() {
            super.run()
            try {
                val url = URL(updateUrl)
                val conn = url.openConnection()
                conn.setRequestProperty("Accept-Encoding", "identity")

                val tm: Array<TrustManager> = arrayOf<TrustManager>(MyX509TrustManager())
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, tm, SecureRandom())
                val ssf: SSLSocketFactory = sslContext.socketFactory
                (conn as HttpsURLConnection).hostnameVerifier = TrustAnyHostnameVerifier()
                (conn as HttpsURLConnection).sslSocketFactory = ssf

                conn.connect()
                val length = conn.contentLength
                val instream = conn.getInputStream()
                val file = File(SAVE_PATH)
                if (!file.exists()) {
                    file.mkdir()
                }
                val apkFile = File(SAVE_PATH, FileUtils.getFileNameFromUrl(updateUrl))
                val fos = FileOutputStream(apkFile)
                var count = 0
                val buf = ByteArray(1024)
                do {
                    val num_read = instream.read(buf)
                    num_read.log()
                    count += num_read
                    val progress = (count.toFloat() / length * 100).toInt()
                    val msg = ApkHandler.obtainMessage()
                    msg.what = DOWNLOADING
                    msg.arg1 = progress
                    ApkHandler.sendMessage(msg)
                    if (num_read < 0) {
                        val msg_done = ApkHandler.obtainMessage()
                        msg_done.what = DOWNLOAD_FINISH
                        ApkHandler.sendMessage(msg_done)
                        break // 跳出循环
                    }
                    if (num_read > 0) {
                        fos.write(buf, 0, num_read)
                    }
                } while (!cancelUpdate)
                cancelUpdate = false
                fos.close()
                instream.close()
            } catch (e: MalformedURLException) {
                ApkHandler.sendEmptyMessage(DOWNLOAD_FAILURE)
            } catch (e: IOException) {
                ApkHandler.sendEmptyMessage(DOWNLOAD_FAILURE)
            }
        }
    }

    private val ApkHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            if (context == null) {
                return
            }
            when (msg.what) {
                DOWNLOADING -> {
                    if (msg.arg1 >= 0) {
                        progress_bar.progress = msg.arg1
                    } else {
                        sendEmptyMessage(DOWNLOAD_FAILURE)
                    }
                }
                DOWNLOAD_FAILURE -> {
                    v_failure.visible()
                    progress_bar.gone()
                    tv_update.text = "重新下载"
                    tv_update.singleClick {
                        progress_bar.visible()
                        v_close.gone()
                        v_failure.gone()
                        tv_title.text = "正在下载"
                        askCancelUpdate()
                        downloadAPK()
                    }
                    tv_title.text = "下载失败"
                }
                DOWNLOAD_FINISH -> {
                    progress_bar.progress = 100
                    tv_update.text = "安   装"
                    tv_update.singleClick {
                        dealWithZip()
                    }
                    tv_title.text = "下载完成"
                    dealWithZip()
                }
            }
        }
    }

    fun setUpdateDialogCallback(callback: UpdateDialogCallback) {
        this.callback = callback
    }

    fun getDiskCacheDir(activity: Activity): String {
        return activity.externalCacheDir?.absolutePath ?: activity.cacheDir.absolutePath
    }

    /**
     * 更新弹框回调
     */
    interface UpdateDialogCallback {
        fun onUpdateCanceled()
    }
}