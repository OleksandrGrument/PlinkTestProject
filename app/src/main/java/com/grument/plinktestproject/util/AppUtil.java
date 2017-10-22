package com.grument.plinktestproject.util;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import jp.wasabeef.glide.transformations.CropSquareTransformation;


public class AppUtil {

    public static void showToastError(Context context, String errorText) {
        Toast.makeText(context, errorText, Toast.LENGTH_SHORT).show();
    }

}