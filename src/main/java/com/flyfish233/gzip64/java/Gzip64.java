package com.flyfish233.gzip64.java;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

// @TargetApi(Build.VERSION_CODES.FROYO)
public class Gzip64 {
    public static String encode(String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gzipOutputStream.write(str.getBytes());
        gzipOutputStream.close();
        // Android
        // return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }

    public static String decode(String encodedStr) throws IOException {
        // Android
        // byte[] compressed = Base64.decode(encodedStr, Base64.DEFAULT);
        byte[] compressed = Base64.getDecoder().decode(encodedStr);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressed);
        GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len;
        while ((len = gzipInputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        byteArrayInputStream.close();

        return byteArrayOutputStream.toString();
    }
}