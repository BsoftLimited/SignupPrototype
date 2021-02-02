package com.bsoft.signupprototype.services;

import android.os.AsyncTask;

public  abstract class Service extends AsyncTask<String, Integer, String> {
    public static interface ServiceListener{
        void onServiceStart();
        void onServiceEnd(String report);
    }

    ServiceListener listener;
    public Service(ServiceListener listener){
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onServiceStart();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listener.onServiceEnd(s);
    }
}

