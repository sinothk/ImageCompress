package com.sinothk.helper.image.compress;

import android.graphics.Bitmap;
import android.util.Log;

import com.sinothk.helper.image.compress.tiny.Tiny;
import com.sinothk.helper.image.compress.tiny.callback.FileBatchCallback;
import com.sinothk.helper.image.compress.tiny.callback.FileCallback;

/**
 * @ author LiangYT
 * @ create 2018/10/13 23:39
 * @ Describe
 */
public class ImageCompress {
    /**
     * 设置调试模式
     *
     * @param isDebug
     */
    public static void setDebug(boolean isDebug) {
        Tiny.getInstance().debug(isDebug);
    }

    /**
     * 开始使用和不再使用时调用：如在Activity中的onCreate,onDestroy。
     */
    public static void compressReset() {
        Runtime runtime = Runtime.getRuntime();
        System.gc();
        runtime.runFinalization();
        System.gc();
    }

    /**
     * 根据图片路径压缩，然后返回压缩后的图片路径：单张。
     *
     * @param oldFilePath
     * @param callback
     */
    public static void execute(final String oldFilePath, final CompressCallback callback) {
        Tiny.FileCompressOptions compressOptions = new Tiny.FileCompressOptions();
        compressOptions.config = Bitmap.Config.ARGB_8888;

        Tiny.getInstance().source(oldFilePath).asFile().withOptions(compressOptions).compress(new FileCallback() {
            @Override
            public void callback(boolean isSuccess, String outfile) {
                if (!isSuccess) {
                    Log.e("压缩结果：", "压缩失败!");
                    callback.compressed(null, null);
                    return;
                }
                callback.compressed(outfile, null);
                Log.e("压缩结果：", "路径 = " + outfile);
            }
        });
    }

    /**
     * 根据图片路径压缩，然后返回压缩后的图片路径：多张。
     *
     * @param pathArray
     * @param callback
     */
    public static void execute(final String[] pathArray, final CompressCallback callback) {
        Tiny.FileCompressOptions compressOptions = new Tiny.FileCompressOptions();
        compressOptions.config = Bitmap.Config.ARGB_8888;// Bitmap.Config.RGB_565

        Tiny.getInstance().source(pathArray).batchAsFile().withOptions(compressOptions).batchCompress(new FileBatchCallback() {
            @Override
            public void callback(boolean isSuccess, String[] outfile) {
                if (!isSuccess) {
                    callback.compressed(null, null);
                    return;
                }
                callback.compressed(null, outfile);
            }
        });
    }
}
