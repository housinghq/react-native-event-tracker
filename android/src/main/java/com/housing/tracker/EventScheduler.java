package com.housing.tracker;


import android.content.Context;

import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class EventScheduler {

    private static final int BATCH_COUNT = 10;                              //10 events
    private static final int TIMEOUT = 10000;                               //10 mins
    private static final String FINAL_WORKER_TAG = "final_worker";          //final work request tag
    private static final String BATCH_WORKER_TAG = "batch_worker";          //batch work request tag


    /**
     * This method adds the event to a queue(shared pref array)
     * Schedule work request based on following cases
     * <p>
     * 1) When first item is added to the queue, a worker(final)
     * is enqueued with 10 min delay to ensure that all remaining
     * events are sent even if app is killed
     * <p>
     * 2) Once the queue size reaches 10 or an unbatched event arrives,
     * a batch worker is scheduled to immediately send all the
     * events in the queue & final worker is reset.
     *
     * @param context
     * @param message     the event to be queued
     * @param isUnBatched flag for high priority events
     */
    public static void enqueueAndScheduleEvent(Context context, String message, boolean isBatched) {
        int eventQueueSize = EventQueueHelper.enqueueEvent(context, message);
         if (eventQueueSize >= BATCH_COUNT || !isBatched) {
            WorkManager.getInstance().cancelAllWorkByTag(FINAL_WORKER_TAG);
            WorkManager.getInstance().enqueue(buildWorkRequest(false));
        } else if (eventQueueSize == 1) {
            WorkManager.getInstance().enqueue(buildWorkRequest(true));

        }
    }

    private static OneTimeWorkRequest buildWorkRequest(boolean isBatched) {
        OneTimeWorkRequest.Builder workRequestBuilder =
                new OneTimeWorkRequest.Builder(EventTrackerWorker.class)
                        .setConstraints(getConstraints());
        if (isBatched) {
            workRequestBuilder.setInitialDelay(TIMEOUT, TimeUnit.MILLISECONDS)
                    .addTag(FINAL_WORKER_TAG);
        } else {
            workRequestBuilder.addTag(BATCH_WORKER_TAG);
        }

        return workRequestBuilder.build();
    }

    private static Constraints getConstraints() {
        Constraints.Builder constraintsBuilder = new Constraints.Builder();
        constraintsBuilder.setRequiredNetworkType(NetworkType.CONNECTED);
        return constraintsBuilder.build();
    }
}
