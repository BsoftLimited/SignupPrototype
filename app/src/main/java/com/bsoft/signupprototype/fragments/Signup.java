package com.bsoft.signupprototype.fragments;

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
import com.bsoft.signupprototype.item.Detail;
import com.bsoft.signupprototype.utils.Util;

public class Signup extends BaseFragmet implements View.OnClickListener {
    AutoCompleteTextView name, surname, email, username, password, repasswprd;

    protected Signup(Main parent) {
        super(parent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.submit).setOnClickListener(this);
        view.findViewById(R.id.login).setOnClickListener(this);

        name = view.findViewById(R.id.name);
        name.setTag("Name");

        surname = view.findViewById(R.id.surname);
        surname.setTag("Surname");

        email = view.findViewById(R.id.email);
        email.setTag("Email");

        password = view.findViewById(R.id.password);
        name.setTag("Password");

        repasswprd = view.findViewById(R.id.repassword);
        repasswprd.setTag("Repassword");

        username = view.findViewById(R.id.username);
        name.setTag("Username");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                parent.setFragment(new Login(parent));
                break;
            case R.id.submit:
                if(Util.isFormValid(requireContext(), name, surname, email, username, password, repasswprd)){
                    if(password.getText().toString().equals(repasswprd.getText().toString())){
                        register();
                    }else{
                        Toast.makeText(requireContext(), "Password Mismatch", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    public void register(){
        String uid = Util.generateUid(username.getText().toString());
        Detail detail = new Detail(uid, name.getText().toString(), surname.getText().toString());
        detail.setEmail(email.getText().toString());
        detail.setUsername(username.getText().toString());
        detail.setPassword(password.getText().toString());

        parent.addDetail(detail);
        parent.setFragment(new Success(parent, detail));
    }
}
