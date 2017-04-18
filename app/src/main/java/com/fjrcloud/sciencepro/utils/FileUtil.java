package com.fjrcloud.sciencepro.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lin on 2016/9/26.
 */
public class FileUtil {

    /**
     * 获取图片的存储路径
     * @param activity
     * @return
     */
    public static String getSaveImagePath(Activity activity) {
        String savePath = Environment.getExternalStorageDirectory().getAbsolutePath() +  "/" + getApplicationName(activity) + "/download/image";
        File dirFile = new File(savePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return savePath;
    }

    /**
     * 获取存储路径
     * @return
     */
    public static String getSaveFilePath(Activity activity) {
        String savePath = Environment.getExternalStorageDirectory().getAbsolutePath() +  "/" + getApplicationName(activity) + "/download";
        File dirFile = new File(savePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return savePath;
    }

    /**
     * 获取应用名
     * @return
     */
    public static String getApplicationName(Activity activity) {
        PackageManager manager = null;
        ApplicationInfo info = null;
        try {
            manager = activity.getApplicationContext().getPackageManager();
            info = manager.getApplicationInfo(activity.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            info = null;
        }
        String applicationName = (String) manager.getApplicationLabel(info);
        return applicationName;
    }

    /**
     * 将得到的一个bitmap保存到sd上，得到一个绝对路径
     */
    public static String getPath(Bitmap bitmap, Activity activity) {
        File temDir = new File(getSaveImagePath(activity));
        if (!temDir.exists()) {
            temDir.mkdirs();
        }
        File image = new File(temDir.getAbsolutePath() + "/head.png");
        try {
            FileOutputStream outputStream = new FileOutputStream(image);
            bitmap.compress(Bitmap.CompressFormat.PNG, 85, outputStream);
            outputStream.flush();
            outputStream.close();
            return image.getCanonicalPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取文件的路径
     * @param context
     * @param uri
     * @return
     */
    public static String getRealFilePath(final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null ) {
            data = uri.getPath();
        }
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    public static int getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int versionCode = packageInfo.versionCode;
            return versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            //没有找到包名的异常
            e.printStackTrace();
        }
        return -1;
    }


    /**
     * 获取存储地址
     *
     * @return
     */
    public static String getSaveDir(Context context) {
        String dir;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            dir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath();
        } else {
            dir = context.getFilesDir().getAbsolutePath();
        }
        return dir;
    }

    public static String getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return (cachePath + File.separator + uniqueName);
    }


    public static File getOutputMediaFileUri() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Night");
        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdir()){
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyymmdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
    }
}
