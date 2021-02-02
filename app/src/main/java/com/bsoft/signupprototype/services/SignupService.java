package com.bsoft.signupprototype.services;

import android.util.Log;

import com.bsoft.signupprototype.item.Detail;
import com.bsoft.signupprototype.utils.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SignupService extends Service{
    Detail detail;

    public SignupService(ServiceListener listener, Detail detail){
        super(listener);
        this.detail = detail;
    }

    @Override
    protected String doInBackground(String... params) {
        try{
            return signup(params);
        }catch(Exception e){
            return "unkown error";
        }
    }

    protected String signup(String[] params) {
        StringBuilder output = new StringBuilder("Unknown Error");
        try {
            URL url = new URL(Util.HomeUrl + "api/prototype/create.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            JSONObject jsonParams = new JSONObject();

            jsonParams.put("uid", detail.getUid());
            jsonParams.put("name", detail.getName());
            jsonParams.put("surname", detail.getSurname());
            jsonParams.put("username", detail.getUsername());
            jsonParams.put("password", detail.getPassword());
            jsonParams.put("email", detail.getEmail());
            jsonParams.put("number", detail.getNumber());
            jsonParams.put("state", detail.getState());
            jsonParams.put("lga", detail.getLga());

            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            Log.d("data", jsonParams.toString());
            os.writeBytes(jsonParams.toString());
            os.flush();
            os.close();

            InputStreamReader in;
            try{
                in = new InputStreamReader(conn.getErrorStream());
            } catch (Exception ex){
                in = new InputStreamReader(conn.getInputStream());
            }

            StringBuilder feedback = new StringBuilder();
            int init;
            while ((init = in.read()) != -1) {
                feedback.append((char) init);
            }
            in.close();

            Log.e("server response", feedback.toString());
            return new JSONObject(feedback.toString()).getString("message");
        } catch (Exception e) {
            e.printStackTrace();
            output = new StringBuilder(e.getMessage());
        }
        return output.toString();
    }
}
