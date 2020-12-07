package com.lsg.wandroidmvvm.util;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by lsg on 2020/10/28
 */
public class ViewUtil {

    //快速获取textView 或 EditText上文字内容
    public static String getStringByUI(View view) {
        if (view instanceof EditText) {
            return ((EditText) view).getText().toString().trim();
        } else if (view instanceof TextView) {
            return ((TextView) view).getText().toString().trim();
        }
        return "";
    }


}
