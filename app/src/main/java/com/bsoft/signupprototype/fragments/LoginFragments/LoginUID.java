package com.bsoft.signupprototype.fragments.LoginFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bsoft.signupprototype.Main;
import com.bsoft.signupprototype.R;
import com.bsoft.signupprototype.fragments.BaseFragmet;
import com.bsoft.signupprototype.fragments.Preview;
import com.bsoft.signupprototype.fragments.Signup;
import com.bsoft.signupprototype.item.Detail;
import com.bsoft.signupprototype.services.LoginService;
import com.bsoft.signupprototype.services.Service;
import com.bsoft.signupprototype.utils.Util;

public class LoginUID extends BaseFragmet implements View.OnClickListener, Service.ServiceListener {
    AutoCompleteTextView uid;
    LoginService service;

    public LoginUID(Main parent) {
        super(parent);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_uid, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.submit).setOnClickListener(this);
        view.findViewById(R.id.sigup_btn).setOnClickListener(this);
        uid = view.findViewById(R.id.uid);
        if(parent.getCredential() != null){
            uid.setText(parent.getCredential().getUid());
        }
        uid.setTag("UID");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:
                if(Util.isFormValid(requireContext(), uid)){
                    service = new LoginService(this);
                    service.setMethod(LoginService.LoginMethod.UID);
                    service.execute(uid.getText().toString());;
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
        if (service.getDetail() != null) {
            parent.setFragment(new Preview(parent, service.getDetail()));
        } else {
            Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_LONG).show();
        }
    }
}
