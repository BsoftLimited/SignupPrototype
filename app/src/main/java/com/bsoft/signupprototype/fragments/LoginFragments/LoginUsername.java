package com.bsoft.signupprototype.fragments.LoginFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bsoft.signupprototype.Main;
import com.bsoft.signupprototype.R;
import com.bsoft.signupprototype.fragments.BaseFragmet;
import com.bsoft.signupprototype.fragments.Preview;
import com.bsoft.signupprototype.fragments.Signup;
import com.bsoft.signupprototype.services.LoginService;
import com.bsoft.signupprototype.services.Service;
import com.bsoft.signupprototype.utils.Util;

public class LoginUsername extends BaseFragmet implements View.OnClickListener , Service.ServiceListener {
    EditText username, password;
    LoginService service;

    public LoginUsername(Main parent) {
        super(parent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_username, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.submit).setOnClickListener(this);
        view.findViewById(R.id.sigup_btn).setOnClickListener(this);
        username = view.findViewById(R.id.username);
        username.setTag("Username");
        password = view.findViewById(R.id.password);
        password.setTag("Password");
        if(parent.getCredential() != null){
            username.setText(parent.getCredential().getUsername());
            password.setText(parent.getCredential().getPassword());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:
                if(Util.isFormValid(requireContext(), username, password)){
                    service = new LoginService(this);
                    service.setMethod(LoginService.LoginMethod.UserName);
                    service.execute(username.getText().toString(), password.getText().toString());
                }
                break;
            case R.id.sigup_btn:
                parent.setFragment(new Signup(parent));
                break;
        }
    }

    @Override
    public void onServiceStart() {
        parent.setLoading("Loging in, Please wait");
    }

    @Override
    public void onServiceEnd(String report) {
        parent.clearLoading();
        if(service.getDetail() != null){
            parent.setFragment(new Preview(parent, service.getDetail()));
        }else{
            Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_LONG).show();
        }
    }
}