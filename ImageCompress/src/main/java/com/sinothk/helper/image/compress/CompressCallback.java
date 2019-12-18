package com.sinothk.helper.image.compress;

/**
 * @ author LiangYT
 * @ create 2018/10/13 23:42
 * @ Describe
 */
// 定义回调接口
public interface CompressCallback {
    void compressed(String path, String[] pathArr);
}
