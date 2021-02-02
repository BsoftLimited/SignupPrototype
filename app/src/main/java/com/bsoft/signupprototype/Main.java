package com.bsoft.signupprototype;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bsoft.signupprototype.fragments.Login;
import com.bsoft.signupprototype.fragments.Signup;
import com.bsoft.signupprototype.item.Credential;
import com.bsoft.signupprototype.utils.Util;


public class Main extends AppCompatActivity {
    private View loading;
    private TextView loadingText;
    private boolean isSignup = false;

    Credential credential;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        credential = Util.retrieveCredential(this);
        loading = findViewById(R.id.loading);
        loadingText = findViewById(R.id.loading_text);

        setFragment(new Login(this));
    }

    public void setFragment(Fragment fragment){
        isSignup = fragment instanceof Signup;
        Util.loadFragment(this, fragment, R.id.container);
    }

    public void saveDetails(){
        Util.saveCredential(this, credential);
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    @Override
    public void finish() {
        this.saveDetails();
        super.finish();
    }

    public Credential getCredential() {
        return credential;
    }

    public void setLoading(String message){
        loading.setVisibility(View.VISIBLE);
        loadingText.setText(message);
    }

    public void clearLoading(){
        loading.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        if(isSignup){
            setFragment(new Login(this));
        }else {
            super.onBackPressed();
        }
    }
}