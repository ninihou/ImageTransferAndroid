package idv.david.imagetransferandroid;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class Util {
    // Android官方模擬器連結本機web server可以直接使用 http://10.0.2.2
//    public final static String URL = "http://192.168.196.157:8081/ImageTransferWeb/ImageUploadServlet";
    public final static String URL = "http://10.0.2.2:8081/ImageTransferWeb/ImageUploadServlet";
    public final static String PREF_FILE = "preference";

    public static byte[] bitmapToPNG(Bitmap srcBitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 轉成PNG不會失真，所以quality參數值會被忽略
        srcBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /*
     * options.inJustDecodeBounds取得原始圖片寬度與高度資訊 (但不會在記憶體裡建立實體)
     * 當輸出寬與高超過自訂邊長邊寬最大值，scale設為2 (寬變1/2，高變1/2)
     */
    public static int getImageScale(String imagePath, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, options);
        int scale = 1;
        while (options.outWidth / scale >= width ||
                options.outHeight / scale >= height) {
            scale *= 2;
        }
        return scale;
    }
}
