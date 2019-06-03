package com.housing.tracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class EventTrackerWorker extends Worker {

    public EventTrackerWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        JSONArray eventsArray = EventQueueHelper.dequeueEvents(getApplicationContext());
        try {
            JSONObject eventsData = new JSONObject().put("message", eventsArray);
            NetworkService.getInstance().post(NetworkService.URL, eventsData);

        } catch (JSONException e) {
            return Result.failure();
        }

        return Result.success();
    }
}
