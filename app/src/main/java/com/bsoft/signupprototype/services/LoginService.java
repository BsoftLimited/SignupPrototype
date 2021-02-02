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

public class LoginService extends Service{
	public static enum LoginMethod{UserName, UID}

	Detail detail;
	LoginMethod method;
	public LoginService(ServiceListener listener){
		super(listener);
		method = LoginMethod.UserName;
	}

	public Detail getDetail() {
		return detail;
	}

	public void setMethod(LoginMethod method) {
		this.method = method;
	}

	@Override
	protected String doInBackground(String... params) {
		try{
			return login(params);
		}catch(Exception e){
			return "unkown error";
		}
	}
	
	protected String login(String[] params) {
		StringBuilder output = new StringBuilder("Unknown Error");
		try {
			URL url =  method == LoginMethod.UID ? new URL(Util.HomeUrl + "api/prototype/read.php") : new URL(Util.HomeUrl + "api/prototype/read_username.php");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			conn.setRequestProperty("Accept", "application/json");
			conn.setDoOutput(true);
			conn.setDoInput(true);

			JSONObject jsonParams = new JSONObject();

			switch (method){
				case UID:
					jsonParams.put("uid", params[0]);
					break;
				case UserName:
					jsonParams.put("username", params[0]);
					jsonParams.put("password", params[1]);
					break;
			}

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
			JSONArray jsonarray = new JSONArray(feedback.toString());
			for (int i = 0; i < jsonarray.length(); i++) {
				JSONObject jsonobject = jsonarray.getJSONObject(i);
				if (detail == null) {
					detail = new Detail(jsonobject.getString("uid"), jsonobject.getString("username"), jsonobject.getString("password"));
					detail.setName(jsonobject.getString("name"));
					detail.setSurname(jsonobject.getString("surname"));
					detail.setEmail(jsonobject.getString("email"));
					detail.setState(jsonobject.getString("state"));
					detail.setLga(jsonobject.getString("lga"));
					detail.setNumber(jsonobject.getString("number"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			output = new StringBuilder(e.getMessage());
		}
		return output.toString();
	}
}