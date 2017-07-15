package com.jktaihe.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by jktaihe on 15/7/17.
 * blog: blog.jktaihe.com
 */

public class PhoneUtils {

    public static void callPhone(Context context, String phone) {
        try {
            final Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));

            if (dialIntent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(dialIntent);
            } else {
                ToastUtils.shortToast(context, "请联系客服" + phone);
            }
        } catch (Exception e) {
            ToastUtils.shortToast(context, "请联系客服" + phone);
        }
    }
}
