package com.person.kotlintest.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.person.kotlintest.R;

/**
 * @anthor tr
 * @date 2021/5/17
 * @desc
 */
public class CustomTitleBar extends RelativeLayout {
    private Button titleBarRightBtn;
    private TextView titleBarTitle;

    private ImageView iv_left;
    private TextView tv_left;
    private EditText ed;
    private ImageView delete;
    private View line;

    public CustomTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_title_bar, this, true);
        iv_left = findViewById(R.id.iv_left);
        tv_left = findViewById(R.id.tv_left);
        ed = findViewById(R.id.ed);
        delete = findViewById(R.id.delete);
        line = findViewById(R.id.line);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleBar);
        if (attributes != null) {

            if (attributes.getBoolean(R.styleable.CustomTitleBar_left_img_visible, true)) {
                iv_left.setVisibility(View.VISIBLE);
            } else {
                iv_left.setVisibility(View.GONE);
            }
            iv_left.setImageResource(attributes.getResourceId(R.styleable.CustomTitleBar_left_img, R.mipmap.ic_launcher));
            tv_left.setText(attributes.getString(R.styleable.CustomTitleBar_left_textwww));
            ed.setHint(attributes.getString(R.styleable.CustomTitleBar_ed_hint));
            attributes.recycle();
        }

        delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ed.setText("");
            }
        });
        ed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    delete.setVisibility(GONE);
                } else {
                    delete.setVisibility(VISIBLE);
                }
            }
        });
        ed.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (ed.getText().toString().isEmpty()) {
                        delete.setVisibility(GONE);
                    } else {
                        delete.setVisibility(VISIBLE);
                    }
                    line.setBackgroundResource(R.color.black);
                } else {
                    delete.setVisibility(GONE);
                    line.setBackgroundResource(R.color.blue_2a8cff);
                }
            }
        });
    }

    public void setLeftClickListener(OnClickListener onClickListener) {
        if (onClickListener != null) {
            iv_left.setOnClickListener(onClickListener);
        }
    }
}