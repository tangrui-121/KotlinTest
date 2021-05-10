//package com.person.kotlintest.unuse
//
//import android.app.Activity
//import android.content.Context
//import android.content.Intent
//import android.net.Uri
//import android.os.Build
//import android.text.TextUtils
//import androidx.core.content.FileProvider
//import java.io.File
//import java.math.BigInteger
//import java.util.regex.Pattern
//
///**
// * @anthor tr
// * @date 2021/4/19
// * @desc 更新相关方法工具类
// */
//object UpdateUtils {
//
//    /**
//     * 安装下载完的apk
//     */
//    fun installApp(context: Context, apkFile: File) {
//        val uri: Uri
//        val intent = Intent()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            uri = FileProvider.getUriForFile(
//                context,
//                context.applicationContext.packageName + ".fileProvider", //调用获取当前主app的application的包名为前缀的provider
//                apkFile
//            )
//        } else {
//            uri = Uri.fromFile(apkFile)
//        }
//        intent.action = Intent.ACTION_VIEW
//        intent.setDataAndType(uri, "application/vnd.android.package-archive")
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//        context.startActivity(intent)
//    }
//
//
//    /**
//     * 包体缓存路径
//     */
//    fun getDiskCacheDir(activity: Activity): String {
//        return activity.externalCacheDir?.absolutePath ?: activity.cacheDir.absolutePath
//
//    }
//
//
//    /**
//     * 两个版本之间的判断比对
//     * 返回值：1:更新版本大于本地版本，进行更新
//     *       -1：更新版本小于本地版本，不进行更新
//     *        0：同版本，默认不更新
//     */
//    @JvmStatic
//    fun compareVersion(updateVersion: String?, localVersion: String?): Int {
//        val first = updateVersion!!.replace("V", "")
//        val second = localVersion!!.replace("V", "")
//        if (verifyVersion(first) && verifyVersion(second)) {
//            val firstSplits = first!!.split(".")
//            val secondSplits = second!!.split(".")
//            val len = Math.max(firstSplits.size, secondSplits.size)
//
//            for (i in 0 until len) {
//                val firstElement: String
//                if (i >= firstSplits.size) {
//                    firstElement = "0"
//                } else {
//                    firstElement = firstSplits[i]
//                }
//
//                val secondElement: String
//                if (i >= secondSplits.size) {
//                    secondElement = "0"
//                } else {
//                    secondElement = secondSplits[i]
//                }
//
//                val compareResult = compareNum(firstElement, secondElement)
//                if (compareResult == 1) {
//                    return 1
//                }
//
//                if (compareResult == -1) {
//                    return -1
//                }
//            }
//        } else if (verifyVersion(first) && !verifyVersion(second)) {
//            return 1
//        }
//        return 0
//    }
//
//
//    /**
//     * 版本数值比对
//     */
//    @JvmStatic
//    fun compareNum(first: String?, second: String?): Int {
//        if (verifyNum(first) && verifyNum(second)) {
//            val firstValue = BigInteger(first)
//            val secondValue = BigInteger(second)
//            return firstValue.compareTo(secondValue)
//        } else {
//            return 0
//        }
//    }
//
//    /**
//     *数值文本校验
//     */
//    @JvmStatic
//    fun verifyNum(num: String?): Boolean {
//        if (num.isNullOrEmpty()) {
//            return false
//        }
//        val pattern = Pattern.compile("^\\d+$")
//        return pattern.matcher(num).matches()
//    }
//
//    /**
//     * 版本文本校验
//     */
//    @JvmStatic
//    fun verifyVersion(version: String?): Boolean {
//        if (TextUtils.isEmpty(version)) {
//            return false
//        } else {
//            val pattern = Pattern.compile("^([0-9]\\d*\\.)(\\d+\\.)*\\d+$")
//            val matcher = pattern.matcher(version)
//            return matcher.matches()
//        }
//    }
//}