package com.fjrcloud.sciencepro.ui.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.utils.Constants;
import com.fjrcloud.sciencepro.utils.ImgLoadUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * Created by lin on 2016/7/6.
 * 上传头像 图片选择/ 报警台图片选择
 */
public class SelectPicActivity extends BaseToolbarActivity {

    public static final String INTENT_SELECTED_PICTURE = "intent_selected_picture";

    private GridView mPicGridView;

    private Context mContext;
    private static final int TAKE_PICTURE = 666;
    private Map<String, Integer> mTmpDir;
    private List<ImageFloder> mDirPaths;
    private ContentResolver mContentResolver;
    private ImageFloder mImageAll, mCurrentImageFolder;
    private String cameraPath = null;
    private PictureAdapter mPictureAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int provideContentView() {
        return R.layout.activity_select_pic;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && cameraPath != null) {
            Intent data2 = new Intent();
            data2.putExtra(INTENT_SELECTED_PICTURE, cameraPath);
            setResult(RESULT_OK, data2);
            finish();
        }
    }

    @Override
    public void initData() {
        mTmpDir = new HashMap<>();
        mDirPaths = new ArrayList<>();
        mImageAll = new ImageFloder();
        mImageAll.setDir("/所有图片");
        mCurrentImageFolder = mImageAll;
        mDirPaths.add(mImageAll);
        mContext = this;
        mContentResolver = getContentResolver();
        getThumbnail();
    }

    @Override
    public void initAdapter() {
        mPictureAdapter = new PictureAdapter();

    }

    @Override
    public void initView() {
        setTitle("图片");
        mPicGridView = (GridView) findViewById(R.id.user_image_gridview);
        mPicGridView.setAdapter(mPictureAdapter);
    }

    @Override
    public void initListener() {
        mPicGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    goCamare();
                } else {
                    Intent intent2Choice = new Intent();
                    intent2Choice.putExtra(INTENT_SELECTED_PICTURE, String.valueOf(mCurrentImageFolder.mImages.get(position - 1).path));
                    setResult(RESULT_OK, intent2Choice);
                    finish();
                }
            }
        });

    }


    private void goCamare() {
        PermissionGen.with(SelectPicActivity.this)
                .permissions(Manifest.permission.CAMERA)
                .addRequestCode(Constants.PERMISSION_CODE)
                .request();
    }

    public Uri getOutputMediaFileUri() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Property");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdir()) {
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyymmdd_HHmmss").format(new Date());
        File mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        cameraPath = mediaFile.getAbsolutePath();
        return Uri.fromFile(mediaFile);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = Constants.PERMISSION_CODE)
    private void permissionSuccess() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri imageUri = getOutputMediaFileUri();
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    @PermissionFail(requestCode = Constants.PERMISSION_CODE)
    private void permissionFailure() {
        showShortToast(getString(R.string.permission_failure));
    }


    class PictureAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mCurrentImageFolder.mImages.size() + 1;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.gridview_selected_pic_item, null);
                holder = new ViewHolder();
                holder.mImageView = (ImageView) convertView.findViewById(R.id.post_grid_item_iv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            if (position == 0) {
                holder.mImageView.setImageResource(R.drawable.img_camera);
            } else {
                position = position - 1;

                final ImageItem item = mCurrentImageFolder.mImages.get(position);
                ImgLoadUtils.loadUrl(SelectPicActivity.this, "file://" + item.path, holder.mImageView);
            }
            return convertView;
        }

        class ViewHolder {

            ImageView mImageView;

        }
    }

    /**
     * 获取略缩图
     */
    private void getThumbnail() {
        Cursor cursor = mContentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.ImageColumns.DATA}, "", null,
                MediaStore.MediaColumns.DATE_ADDED + " DESC");

        if (cursor.moveToFirst()) {
            int _data = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            do {
                //获取图片的路径
                String path = cursor.getString(_data);
                mImageAll.mImages.add(new ImageItem(path));
                //获取图片的父路径名
                File parentFile = new File(path).getParentFile();
                if (parentFile == null) {
                    continue;
                }
                ImageFloder imageFloder = null;
                String dirPath = parentFile.getAbsolutePath();
                if (!mTmpDir.containsKey(dirPath)) {
                    imageFloder = new ImageFloder();
                    imageFloder.setDir(dirPath);
                    imageFloder.setFirstImagePath(path);
                    mDirPaths.add(imageFloder);
                    mTmpDir.put(dirPath, mDirPaths.indexOf(imageFloder));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        mTmpDir = null;
    }

    class ImageFloder {

        /**
         * 图片的文件夹路径
         */
        private String mDir;

        /**
         * 第一张图片的路径
         */
        private String mFirstImagePath;

        /**
         * 文件夹的名称
         */
        private String mName;

        public List<ImageItem> mImages = new ArrayList<>();

        public String getDir() {
            return mDir;
        }

        public void setDir(String dir) {
            mDir = dir;
            int lastIndexOf = mDir.lastIndexOf("/");
            mName = dir.substring(lastIndexOf);
        }

        public String getFirstImagePath() {
            return mFirstImagePath;
        }

        public void setFirstImagePath(String firstImagePath) {
            mFirstImagePath = firstImagePath;
        }

        public String getName() {
            return mName;
        }

    }

    class ImageItem {

        String path;

        public ImageItem(String path) {
            this.path = path;
        }
    }
}
