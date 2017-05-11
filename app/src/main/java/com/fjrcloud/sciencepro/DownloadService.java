package com.fjrcloud.sciencepro;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.fjrcloud.sciencepro.data.Download;
import com.fjrcloud.sciencepro.data.net.FileEntity;
import com.fjrcloud.sciencepro.network.DownloadRequestManager;
import com.fjrcloud.sciencepro.network.ProgressResponseListener;
import com.fjrcloud.sciencepro.utils.Constants;
import com.fjrcloud.sciencepro.utils.FileUtil;
import com.fjrcloud.sciencepro.utils.StringUtils;

import java.io.File;
import java.io.InputStream;

import rx.Subscriber;

/**
 * Created by lin on 2017/3/3.
 */

public class DownloadService extends IntentService {
    private static final String TAG = "DownloadService";

//        private String apkUrl = "http://download.fir.im/v2/app/install/5818acbcca87a836f50014af?download_token=a01301d7f6f8f4957643c3fcfe5ba6ff";
//    private String apkUrl = "http://172.30.16.24:8080/tech/upload/guide/20/c0816251-982d-4487-9165-4266d1d0b226.xls";
    private NotificationManager mNotificationManager;
    private Notification.Builder mBuilder;
    private File mFile;
    private FileEntity fileEntity;


    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        fileEntity = intent.getParcelableExtra(Constants.DATA);

        if (fileEntity != null) {
            mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mBuilder = new Notification.Builder(this)
                    .setSmallIcon(R.mipmap.icon_download)
                    .setContentText("Downloading File")
                    .setContentTitle("Download")
                    .setAutoCancel(true);

            mNotificationManager.notify(0, mBuilder.build());
            download();
        }
    }

    private void download() {
        ProgressResponseListener listener = new ProgressResponseListener() {
            @Override
            public void onResponseProgress(long bytesRead, long contentLength, boolean done) {
                int progress = (int) ((bytesRead * 100) / contentLength);
                Log.d(TAG, "progress:" + progress);
                Download download = new Download();
                download.setCurrentFileSize(bytesRead);
                download.setTotalFileSize(contentLength);
                download.setProgress(progress);
                sendNotification(download);
            }
        };

        mFile = new File(FileUtil.getDiskCacheDir(App.getInstance(), fileEntity.getName()));
        if (mFile.exists()) {
            mFile.delete();
        }
        String apkUrl = Constants.BASE_IMG_URL + fileEntity.getFilesPath();
        new DownloadRequestManager(StringUtils.getHostName(apkUrl), listener).downloadApk(apkUrl, mFile, new Subscriber<InputStream>() {
            @Override
            public void onCompleted() {
                mNotificationManager.cancel(0);
                // 没用啊 - - 因为service 结束了？
//                mBuilder = new Notification.Builder(DownloadService.this)
//                        .setSmallIcon(R.mipmap.icon_logo)
//                        .setContentText("Downloading File ok")
//                        .setContentTitle("Download")
//                        .setAutoCancel(true);
//
//                mNotificationManager.notify(1, mBuilder.build());
                Toast.makeText(App.getInstance(), "下载完成", Toast.LENGTH_SHORT).show();
//                Toast.makeText(DownloadService.this, "completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(InputStream inputStream) {

            }
        });
    }

    private void sendNotification(Download download) {
        if (download.getProgress() == 100) {

        } else {
            mBuilder.setProgress(100, download.getProgress(), false)
                    .setContentText(StringUtils.getDataSize(download.getCurrentFileSize()) + "/"
                            + StringUtils.getDataSize(download.getTotalFileSize()));
            mNotificationManager.notify(0, mBuilder.build());
        }

    }


}
