package com.norsepotions.burlesquenamegenerator;

import android.app.Application;

import com.firebase.client.Firebase;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class BurlesqueNameGeneratorApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
            .setDefaultFontPath("fonts/Montserrat-Regular.otf")
            .setFontAttrId(R.attr.fontPath)
            .build());
    }
}
