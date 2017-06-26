package com.smarty.civis.data.content;

import android.net.Uri;

/**
 * Created by mohammed on 6/26/17.
 */

public class CivisContract
{
    public static final String AUTHORITY = "com.smarty.civis";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+AUTHORITY);
}
