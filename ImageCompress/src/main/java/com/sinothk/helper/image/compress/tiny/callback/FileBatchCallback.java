package com.sinothk.helper.image.compress.tiny.callback;

/**
 * Created by zhengxiaoyong on 2017/3/31.
 */
public interface FileBatchCallback extends Callback {

    void callback(boolean isSuccess, String[] outfile);

}
