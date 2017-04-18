package com.fjrcloud.sciencepro.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.adapter.UploadGalleryAdapter;
import com.fjrcloud.sciencepro.data.Pic;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.utils.Constants;
import com.fjrcloud.sciencepro.utils.FileUtil;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

import static com.fjrcloud.sciencepro.utils.Constants.PIC_PHOTO;

/**
 * 写信
 */
public class WriteLetterActivity extends BaseToolbarActivity {


    private EditText mEditWriteLetterTitle;
    /**
     * 填写具体内容...
     */
    private EditText mEditLetterContent;
    private RecyclerView mRvLetterGallery;
    private UploadGalleryAdapter mAdapter;

    @Override
    protected int provideContentView() {
        return R.layout.activity_write_letter;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initAdapter() {
        mAdapter = new UploadGalleryAdapter(R.layout.recycler_horizontal_gallery_item);
    }

    @Override
    public void initView() {
        setTitle("写信");
        setRightTv("发表", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mEditWriteLetterTitle = (EditText) findViewById(R.id.edit_write_letter_title);
        mEditLetterContent = (EditText) findViewById(R.id.edit_letter_content);
        mRvLetterGallery = (RecyclerView) findViewById(R.id.rv_letter_gallery);

        mRvLetterGallery.setHasFixedSize(true);
        mRvLetterGallery.setLayoutManager(new GridLayoutManager(this, 3));
        mRvLetterGallery.setAdapter(mAdapter);
        mRvLetterGallery.setFocusable(true);
    }

    @Override
    public void initListener() {
        mRvLetterGallery.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_gallery_show_pic:
                        if (view.getTag(R.id.add_pic) != null &&
                                TextUtils.equals(view.getTag(R.id.add_pic).toString(), UploadGalleryAdapter.ADD)) {
                            PermissionGen.with(WriteLetterActivity.this)
                                    .permissions(Manifest.permission.READ_EXTERNAL_STORAGE)
                                    .addRequestCode(Constants.PERMISSION_CODE)
                                    .request();
                        }

                        break;
                    case R.id.iv_gallery_cancel:
                        adapter.remove(position);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == PIC_PHOTO) {
            if (data != null) {
                Uri uri = Uri.parse(data.getStringExtra(SelectPicActivity.INTENT_SELECTED_PICTURE));
                String imgPath = FileUtil.getRealFilePath(this, uri);
                if (imgPath != null) {
                    mAdapter.addData(0, new Pic(imgPath));
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = Constants.PERMISSION_CODE)
    private void permissionSuccess() {
        openActivityForResult(SelectPicActivity.class, PIC_PHOTO);
    }

    @PermissionFail(requestCode = Constants.PERMISSION_CODE)
    private void permissionFailure() {
        showShortToast(getString(R.string.permission_failure));
    }
}
