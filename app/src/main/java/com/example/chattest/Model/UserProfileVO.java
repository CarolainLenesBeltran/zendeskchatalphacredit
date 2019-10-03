package com.example.chattest.Model;

import android.net.Uri;

public class UserProfileVO {

    private final String name;
    private final String email;
    private final Uri uri;

    public UserProfileVO(String name, String email, Uri uri) {
        this.name = name;
        this.email = email;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Uri getUri() {
        return uri;
    }


}
