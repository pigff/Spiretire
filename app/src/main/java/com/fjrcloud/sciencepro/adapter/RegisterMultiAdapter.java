package com.fjrcloud.sciencepro.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.multi.CommonMulti;
import com.fjrcloud.sciencepro.data.multi.LoginItemMulti;

import java.util.List;

/**
 * Created by greedy on 17/4/1.
 */

public class RegisterMultiAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int TITLE = 0;
    public static final int LOGIN_EDIT = 1;
    public static final int LOGIN_TEXT = 2;
    public static final int LOGIN_EDIT_TEXT = 3;
    public static final int IMAGE = 4;
    public static final int FILE = 5;
    public static final int ALL_DELETE = 6;
    public static final int DIVIDING = 7;

    private EditChangeListener mListener;

    public RegisterMultiAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TITLE, R.layout.recycler_multi_login_title_item);
        addItemType(LOGIN_EDIT, R.layout.recycler_multi_login_edit_item);
        addItemType(LOGIN_TEXT, R.layout.recycler_multi_login_text_item);
        addItemType(LOGIN_EDIT_TEXT, R.layout.recycler_multi_login_edit_text_item);
        addItemType(IMAGE, R.layout.recycler_multi_login_image_item);
        addItemType(FILE, R.layout.recycler_multi_login_file_item);
        addItemType(ALL_DELETE, R.layout.recycler_multi_login_all_delete);
        addItemType(DIVIDING, R.layout.recycler_multi_drawer_dividing_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MultiItemEntity item) {
        switch (item.getItemType()) {
            case LOGIN_EDIT:
                if (item instanceof LoginItemMulti) {
                    EditText edit = helper.getView(R.id.edit_multi_login_edit_input);
                    edit.addTextChangedListener(new LoginWatcher() {
                        @Override
                        public void afterTextChanged(Editable s) {
                            if (mListener != null) {
                                mListener.common(((LoginItemMulti) item).getTag(), String.valueOf(s));
                            }
                        }
                    });
                    edit.setHint(((LoginItemMulti) item).getPrompt());
                    helper.setText(R.id.tv_multi_login_edit_name, ((LoginItemMulti) item).getName());
                }
                break;
            case LOGIN_TEXT:
                if (item instanceof LoginItemMulti) {
                    helper.setText(R.id.tv_multi_login_text_name, ((LoginItemMulti) item).getName())
                            .setText(R.id.tv_multi_login_text_prompt, ((LoginItemMulti) item).getPrompt())
                            .addOnClickListener(R.id.multi_login_text_group);
                }
                break;
            case LOGIN_EDIT_TEXT:
                if (item instanceof LoginItemMulti) {
                    EditText edit = helper.getView(R.id.edit_multi_login_edit_text_prompt);
                    edit.addTextChangedListener(new LoginWatcher() {
                        @Override
                        public void afterTextChanged(Editable s) {
                            if (mListener != null) {
                                mListener.common(((LoginItemMulti) item).getTag(), String.valueOf(s));
                            }
                        }
                    });
                    edit.setHint(((LoginItemMulti) item).getPrompt());
                    helper.setText(R.id.tv_multi_login_edit_text_name, ((LoginItemMulti) item).getName())
                            .addOnClickListener(R.id.tv_multi_login_edit_text_add);
                }
                break;
            case IMAGE:
                break;
            case FILE:
                break;
            case ALL_DELETE:
                break;
            case TITLE:
                if (item instanceof CommonMulti) {
                    helper.setText(R.id.tv_multi_login_title, ((CommonMulti) item).getOther());
                }
                break;
        }
    }

    private class LoginWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    public void setEditChangeListener(EditChangeListener listener) {
        mListener = listener;
    }

    public interface EditChangeListener {
        void common(String tag, String content);
    }
}
