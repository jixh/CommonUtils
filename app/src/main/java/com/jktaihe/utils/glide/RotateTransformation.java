package com.jktaihe.utils.glide;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * Created by hzjixiaohui on 2016/6/22.
 *
 */
public class RotateTransformation extends BitmapTransformation {

    private static final String ID = "com.jktaihe.glide.RotateTransformation";


    private float rotateRotationAngle = 0f;

    public RotateTransformation(float rotateRotationAngle) {
        super();
        this.rotateRotationAngle = rotateRotationAngle;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Matrix matrix = new Matrix();
        matrix.postRotate(rotateRotationAngle);
        return Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest){
        try {
            messageDigest.update(ID.getBytes(STRING_CHARSET_NAME));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof RotateTransformation;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }
}
