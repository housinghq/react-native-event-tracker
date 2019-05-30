
package com.housing.tracker;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RNEventTrackerModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNEventTrackerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNEventTracker";
    }

    @ReactMethod
    public void setUrl(String url) {
        NetworkService.INSTANCE.URL = url;
    }

    @ReactMethod
    public void sendEvent(String message, boolean isBatched) {
        EventScheduler.enqueueAndScheduleEvent(reactContext, message, isBatched);
    }

}