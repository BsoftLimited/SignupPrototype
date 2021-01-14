package com.bsoft.signupprototype.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bsoft.signupprototype.Main;
import com.bsoft.signupprototype.R;
import com.bsoft.signupprototype.item.Detail;
import com.bsoft.signupprototype.utils.Details;
import com.bsoft.signupprototype.utils.Util;

public class Login extends BaseFragmet implements View.OnClickListener {
    AutoCompleteTextView uid;

    public Login(Main parent) {
        super(parent);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.submit).setOnClickListener(this);
        view.findViewById(R.id.sigup_btn).setOnClickListener(this);
        uid = view.findViewById(R.id.uid);
        uid.setTag("UID");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit:
                if(Util.isFormValid(requireContext(), uid)){
                    process();
                }
                break;
            case R.id.sigup_btn:
                parent.setFragment(new Signup(parent));
                break;
        }
    }

    private void process(){
        boolean found = false;
        for(Detail detail : parent.getDetails()){
            if(detail.getUid().equals(uid.getText().toString())){
                found = true;
                parent.setFragment(new Preview(parent, detail));
                break;
            }
        }

        if(!found) {
            Toast.makeText(requireContext(), "User not registered", Toast.LENGTH_LONG).show();
        }
    }
}
