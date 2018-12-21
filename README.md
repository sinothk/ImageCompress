# ImageCompress
ImageCompress

# 引入
## Step 1. Add the JitPack repository to your build file

    Add it in your root build.gradle at the end of repositories:

      allprojects {
        repositories {
          ...
          maven { url 'https://jitpack.io' }
        }
      }
  
## Step 2. Add the dependency

      dependencies {
              implementation 'com.github.sinothk:ImageCompress:1.0.1221'
      }
      
# 使用
  ## 单图
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
    
  ## 多图
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
