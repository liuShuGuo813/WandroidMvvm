package com.lsg.wandroidmvvm.util;

import android.text.TextUtils;

/**
 * Created by lsg on 2020/11/3
 */
public class FormatUtil {

    public static String formatChapterName(String... names) {
        StringBuilder format = new StringBuilder();
        for (String name : names) {
            if (!TextUtils.isEmpty(name)) {
                if (format.length() > 0) {
                    format.append("·");
                }
                format.append(name);
            }
        }
        return format.toString();
    }

}
