package com.sinothk.helper.image.compress.tiny.callback;

import android.graphics.Bitmap;

/**
 * Created by zhengxiaoyong on 2017/3/31.
 */
public interface BitmapBatchCallback extends Callback {

    void callback(boolean isSuccess, Bitmap[] bitmaps);

}
