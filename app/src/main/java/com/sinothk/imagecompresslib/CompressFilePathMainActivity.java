package com.sinothk.imagecompresslib;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sinothk.helper.image.compress.CompressCallback;
import com.sinothk.helper.image.compress.ImageCompress;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 根据路径压缩，然后返回路径
 */
public class CompressFilePathMainActivity extends AppCompatActivity {

    private TextView textView1, textView2;
    ImageView singleImgIv, singleImg1Iv, singleImg2Iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compress_demo_activity);

        textView1 = (TextView) findViewById(R.id.textView1);
        singleImgIv = (ImageView) findViewById(R.id.singleImgIv);

        textView2 = (TextView) findViewById(R.id.textView2);
        singleImg1Iv = (ImageView) findViewById(R.id.singleImg1Iv);
        singleImg2Iv = (ImageView) findViewById(R.id.singleImg2Iv);

        ImageCompress.setDebug(true);
        ImageCompress.compressReset();

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 单一文件
                saveIntoFile1();
            }
        });


        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveIntoFile2();
            }
        });
    }

    private void saveIntoFile1() {

        try {
            final InputStream is1 = getResources().getAssets().open("test_4.jpg");

            final File outfile1 = new File(getExternalFilesDir(null), "test_4.jpg");

            FileOutputStream fos = new FileOutputStream(outfile1);
            byte[] buffer = new byte[4096];
            int len = -1;
            while ((len = is1.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            is1.close();

            ImageCompress.execute(outfile1.getPath(), new CompressCallback() {
                @Override
                public void compressed(Object obj) {
                    if (obj == null) {
                        return;
                    }
                    String newPath = (String) obj;
                    textView1.setText(newPath);
                    singleImgIv.setImageBitmap(BitmapFactory.decodeFile(newPath));
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveIntoFile2() {

        try {
            final InputStream is1 = getResources().getAssets().open("test_4.jpg");
            final InputStream is2 = getResources().getAssets().open("test_2.jpg");
            final InputStream is3 = getResources().getAssets().open("test_2.jpg");

            final File outfile1 = new File(getExternalFilesDir(null), "batch-test-4.jpg");
            final File outfile2 = new File(getExternalFilesDir(null), "batch-test-2.jpg");
            final File outfile3 = new File(getExternalFilesDir(null), "batch-test-2.jpg");

            FileOutputStream fos = new FileOutputStream(outfile1);
            byte[] buffer = new byte[4096];
            int len = -1;
            while ((len = is1.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos = new FileOutputStream(outfile2);
            buffer = new byte[4096];
            len = -1;
            while ((len = is2.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos = new FileOutputStream(outfile3);
            buffer = new byte[4096];
            len = -1;
            while ((len = is3.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
//            fos = new FileOutputStream(outfile4);
//            buffer = new byte[4096];
//            len = -1;
//            while ((len = is4.read(buffer)) != -1) {
//                fos.write(buffer, 0, len);
//            }
            fos.close();

            is1.close();
            is2.close();
            is3.close();
//            is4.close();

            String[] pathArr = new String[3];
            pathArr[0] = outfile1.getPath();
            pathArr[1] = outfile2.getPath();
            pathArr[2] = outfile3.getPath();

            ImageCompress.execute(pathArr, new CompressCallback() {
                @Override
                public void compressed(Object obj) {
                    if (obj == null) {
                        Log.e("a", "a=" + "compress bitmap failed!");
                    } else {

                        final String[] ps = ((String[]) obj);

                        String content = "";
                        for (int i = 0; i < ps.length; i++) {
                            content += ps[i] + ", ";

                            if (i == 0) {
                                singleImg1Iv.setImageBitmap(BitmapFactory.decodeFile(ps[i]));
                            } else if (i == 1) {
                                singleImg2Iv.setImageBitmap(BitmapFactory.decodeFile(ps[i]));
                            }
                        }

                        Log.e("压缩结果：", "path = " + content);
                        textView2.setText(content);
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageCompress.compressReset();
    }
}

