package com.person.kotlintest.download;

import android.content.Context;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @anthor tr
 * @date 2021/4/23
 * @desc 文件相关工具类
 */
public class FileUtils {

    //将字节数转化为MB
    public static String byteToMB(long size){
        long kb = 1024;
        long mb = kb*1024;
        long gb = mb*1024;
        if (size >= gb){
            return String.format("%.1f GB",(float)size/gb);
        }else if (size >= mb){
            float f = (float) size/mb;
            return String.format(f > 100 ?"%.0f MB":"%.1f MB",f);
        }else if (size > kb){
            float f = (float) size / kb;
            return String.format(f>100?"%.0f KB":"%.1f KB",f);
        }else {
            return String.format("%d B",size);
        }
    }

    /**
     * 解压apk压缩包
     *
     * @param context
     * @param oldPath
     */
    public static void unzipAPK(Context context, String oldPath, String outPath) throws FileNotFoundException {
        String newTempPath = oldPath + "_new";
        InputStream is = null;
        is = new FileInputStream(oldPath);
        unzipFile(context, is, outPath, newTempPath);
        // delete old folder
        cleanDir(new File(outPath));
        //rename new folder
        File folder = new File(newTempPath);
        folder.renameTo(new File(outPath));
    }

    /**
     * 解压文件
     *
     * @param context
     * @param is
     * @param oldPath
     * @param newTempPath
     */
    public static void unzipFile(Context context, InputStream is, String oldPath, String newTempPath) {
        int BUFFER = 4096; // 这里缓冲区我们使用4KB，
        String strEntry; // 保存每个zip的条目名称

        BufferedOutputStream dest = null; // 缓冲输出流
        ZipInputStream zis = new ZipInputStream(is);
        ZipEntry entry; // 每个zip条目的实例
        try {
            while ((entry = zis.getNextEntry()) != null) {
                boolean isDirectory = entry.isDirectory();
                if (isDirectory)
                    continue;
                int count;
                byte data[] = new byte[BUFFER];
                strEntry = entry.getName();
                String filePath = oldPath + File.separator + strEntry;
                filePath = filePath.replace(oldPath, newTempPath);
                File entryFile = new File(filePath);
                File entryDir = new File(entryFile.getParent());
                if (!entryDir.exists()) {
                    entryDir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(entryFile);
                dest = new BufferedOutputStream(fos, BUFFER);
                while ((count = zis.read(data, 0, BUFFER)) != -1) {
                    dest.write(data, 0, count);
                }
                dest.flush();
                try {
                    dest.close();
                } catch (Exception e) {

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            zis.close();
        } catch (Exception e) {

        }
    }

    public static void cleanDir(File dir) {
        if (dir == null || !dir.exists()) {
            return;
        }
        Log.d("FileUtil", "准备清除：" + dir.getAbsolutePath());
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    cleanDir(file);
                }
                Log.d("FileUtil", "清除：" + file.getAbsolutePath());
                file.delete();
            }
        }
        dir.delete();
    }

    /**
     * 根据路径获取文件名+后缀
     * 123.jpg
     * 或者路径的最后一个名字
     *
     * @param path
     * @return
     */
    public static String getFileNameFromUrl(String path) {
        if (path == null || path.trim().isEmpty()) {
            return "";
        }
        int start = path.lastIndexOf("/");
        return path.substring(start + 1, path.length());
    }
}