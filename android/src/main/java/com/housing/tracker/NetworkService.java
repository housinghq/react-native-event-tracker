package com.housing.tracker;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkService {

    static NetworkService INSTANCE;

    private static final long CONNECT_TIMEOUT = 200000;   // 20 seconds
    private static final long READ_TIMEOUT = 200000;      // 20 seconds
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient okHttpClient = null;
    public static String URL = "http://eventrouter.housing.com/api/v0/publish_event/";

    public static final NetworkService getInstance(){
        if(null == INSTANCE){
            INSTANCE = new NetworkService();
        }
        return INSTANCE;
    }

    private NetworkService(){

    }


    /**
     * Method to build and return an OkHttpClient so we can set/get
     * headers quickly and efficiently.
     *
     * @return OkHttpClient
     */
    private OkHttpClient buildClient() {
        if (okHttpClient != null) return okHttpClient;

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS);

        return okHttpClientBuilder.build();
    }

    /**
     * makes okhttp3 post call
     *
     * @param url  endpoint
     * @param data object
     * @return String response
     */
    public String post(String url, JSONObject data) {
        RequestBody body = RequestBody.create(JSON, data.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try {
            Response response = buildClient().newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            return null;
        }
    }
}
