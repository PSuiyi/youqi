package com.mzk.compass.youqi.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mzk.compass.youqi.R;
import com.znz.compass.znzlibray.common.DataManager;
import com.znz.compass.znzlibray.utils.ViewHolder;
import com.znz.compass.znzlibray.views.EditTextWithDel;


public class EditTextPsd extends LinearLayout {
    public boolean isWhite = false;//是否显示白色图片，登录页,注册页
    private EditTextWithDel etPsd;
    private ImageView ivShowPsd;
    private boolean isShow = false;//密码是否显示
    private DataManager mDataManager;

    public EditTextPsd(Context context) {
        super(context);
        init(getContext(), null, 0);
    }


    public EditTextPsd(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(getContext(), attrs, 0);
    }

    public EditTextPsd(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(getContext(), attrs, defStyleAttr);


    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {


        mDataManager = DataManager.getInstance(context);
        LayoutInflater.from(context).inflate(R.layout.view_psw_edit_withshow, this);

        etPsd = ViewHolder.init(this, R.id.etPsdDel);
        ivShowPsd = ViewHolder.init(this, R.id.ivShowPsd);
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.EditTextPsd, defStyleAttr, 0);
        if (a.hasValue(R.styleable.EditTextPsd_hintColor)) {
            etPsd.setHintTextColor(a.getColor(R.styleable.EditTextPsd_hintColor, Color.WHITE));
        }
        if (a.hasValue(R.styleable.EditTextPsd_textColor)) {
            etPsd.setTextColor(a.getColor(R.styleable.EditTextPsd_textColor, Color.WHITE));
        }
        if (a.hasValue(R.styleable.EditTextPsd_hint)) {
            etPsd.setHint(a.getString(R.styleable.EditTextPsd_hint));
        }
        if (a.hasValue(R.styleable.EditTextPsd_imageColor)) {
            isWhite = true;
            ivShowPsd.setImageResource(R.mipmap.ic_launcher);
        }
//        if (StringUtil.isBlank(mDataManager.getValueFromView(etPsd))) {
//            mDataManager.setViewVisibility(ivShowPsd, false);
//            isShow = false;
//        } else {
//            mDataManager.setViewVisibility(ivShowPsd, true);
//            isShow = true;
//        }

        etPsd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (StringUtil.isBlank(s.toString())) {
//                    mDataManager.setViewVisibility(ivShowPsd, false);
//                    isShow = false;
//                } else {
//                    mDataManager.setViewVisibility(ivShowPsd, true);
//                    isShow = true;
//                }
            }
        });


        ivShowPsd.setOnClickListener(v -> {
            mDataManager.moveCursorEnd(etPsd);
            if (isShow) {
                ivShowPsd.setImageResource(R.mipmap.zhengyanguan);
                etPsd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                isShow = false;
            } else {
                ivShowPsd.setImageResource(R.mipmap.zhengyan);
                isShow = true;
                etPsd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        });
    }


    public String getEditText() {
        return etPsd.getText().toString();
    }

    public EditText getPsdView() {
        return etPsd;
    }

    public ImageView getImageView() {
        return ivShowPsd;
    }
}
