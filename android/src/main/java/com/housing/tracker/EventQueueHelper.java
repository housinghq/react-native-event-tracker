package com.housing.tracker;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class EventQueueHelper {

    public static final String PREF = "housing_event_tracker";
    public static final String PREF_QUEUE = "housing_event_tracker_queue";

    private static SharedPreferences getSharedPref(Context ctx) {
        return ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    /**
     * Add an event in shared preferences(as a set of events)
     *
     * @param context
     * @param event
     * @return count of total events in the queue
     */
    public synchronized static int enqueueEvent(Context context, String event) {

        SharedPreferences sharedPreferences = getSharedPref(context);
        Set<String> eventQueue = sharedPreferences.getStringSet(PREF_QUEUE, new HashSet<String>());
        eventQueue.add(event);
        SharedPreferences.Editor edit = getSharedPref(context).edit();
        edit.putStringSet(PREF_QUEUE, eventQueue);
        return eventQueue.size();
    }

    /**
     * Dequeue all the events
     *
     * @param context
     * @return dequeued events in a JSON array
     */
    public synchronized static JSONArray dequeueEvents(Context context) {
        SharedPreferences sharedPreferences = getSharedPref(context);
        Set<String> eventQueue = sharedPreferences.getStringSet(PREF_QUEUE, new HashSet<String>());
        JSONArray eventArray = createEventsJson(eventQueue);
        SharedPreferences.Editor edit = getSharedPref(context).edit();
        edit.putStringSet(PREF_QUEUE, new HashSet<String>());
        return eventArray;
    }

    private static JSONArray createEventsJson(Set<String> events) {
        JSONArray eventArray = new JSONArray();
        for (String event : events) {
            try {
                eventArray.put(new JSONObject(event));
            } catch (JSONException e) {
                //TODO log exception
            }
        }
        return eventArray;
    }
}
