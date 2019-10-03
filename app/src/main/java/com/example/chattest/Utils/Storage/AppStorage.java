package com.example.chattest.Utils.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.chattest.Model.UserProfileVO;
import com.google.gson.Gson;

public class AppStorage {

    private static final String REMEMBER_THE_DATE_STORE = "rtd_dates";

    // Profile keys
    private static final String NAME_KEY = "name";
    private static final String EMAIL_KEY = "email";
    private static final String IMAGE_DATA_KEY = "image_data";

    // Dates
    private static final String DATES = "dates";

    private final Gson gson;
    private final SharedPreferences storage;

    public AppStorage(Context context) {
        this.storage = context.getSharedPreferences(REMEMBER_THE_DATE_STORE, Context.MODE_PRIVATE);
        this.gson = new Gson();
    }

    public void storeUserProfile(UserProfileVO user) {
        String avatarUri = null;

        if (user.getUri() != null) {
            avatarUri = user.getUri().toString();
        }

        storage.edit()
                .putString(NAME_KEY, user.getName())
                .putString(EMAIL_KEY, user.getEmail())
                .putString(IMAGE_DATA_KEY, avatarUri)
                .apply();
    }
}
