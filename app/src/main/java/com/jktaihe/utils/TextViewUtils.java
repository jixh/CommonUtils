package com.jktaihe.utils;

import android.graphics.Color;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import org.xml.sax.XMLReader;

/**
 * Created by jktaihe on 15/7/17.
 * blog: blog.jktaihe.com
 */

public class TextViewUtils {

    public static void addLinkBKKF(TextView tv, String linkTxt, final View.OnClickListener clickListener){
        String url = tv.getText().toString();
        SpannableStringBuilder style = new SpannableStringBuilder(url);
        style.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                if (clickListener!=null)clickListener.onClick(widget);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(ds.linkColor);
                ds.setUnderlineText(false);
            }
        }, url.lastIndexOf(linkTxt), url.lastIndexOf(linkTxt)+linkTxt.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(style);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static Spanned parseRedStr(String str){
        Html.TagHandler tagHandler = new Html.TagHandler() {
            int contentIndex;
            int color;

            private void changeColor(boolean opening, Editable output) {
                if (opening) {//获取当前标签的内容开始位置
                    contentIndex = output.length();
                } else {
                    final int length = output.length();
                    String content = output.subSequence(contentIndex, length).toString();
                    SpannableString spanStr = new SpannableString(content);
                    spanStr.setSpan(new ForegroundColorSpan(color), 0, content.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                    output.replace(contentIndex, length, spanStr);
                }
            }

            @Override
            public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
                if ("red".equals(tag)) {
                    color = Color.parseColor("#ff2785");
                    changeColor(opening, output);
                }
            }
        };
        return Html.fromHtml("<a>"+str+"</a>", null, tagHandler);
    }
}
