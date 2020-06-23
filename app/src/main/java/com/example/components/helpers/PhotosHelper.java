package com.example.components.helpers;

import android.util.Base64;

public class PhotosHelper {
    public static byte[] convertBase64ToBytes(String _base64) {
        return Base64.decode(_base64.getBytes(), Base64.DEFAULT);
    }
}
