package com.example.projektmobilne;

import android.net.Uri;

import androidx.room.TypeConverter;

public class Converters {
    @TypeConverter
    public static String fromUriToString(Uri uri) {
        return uri == null ? null : uri.toString();
    }

    @TypeConverter
    public static Uri fromStringToUri(String uriString) {
        return uriString == null ? null : Uri.parse(uriString);
    }
}
