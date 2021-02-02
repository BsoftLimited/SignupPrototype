package com.bsoft.signupprototype.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bsoft.signupprototype.Main;
import com.bsoft.signupprototype.R;
import com.bsoft.signupprototype.fragments.LoginFragments.LoginUID;
import com.bsoft.signupprototype.item.Detail;

public class Preview extends BaseFragmet {
    Detail detail;
    public Preview(Main parent, Detail detail) {
        super(parent);
        this.detail = detail;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.preview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((TextView)view.findViewById(R.id.uid)).setText(String.format("UID: %s", detail.getUid()));
        ((TextView)view.findViewById(R.id.name)).setText(String.format("Name: %s", detail.getName()));
        ((TextView)view.findViewById(R.id.surname)).setText(String.format("Surname: %s", detail.getSurname()));
        ((TextView)view.findViewById(R.id.email)).setText(String.format("Email: %s", detail.getEmail()));
        ((TextView)view.findViewById(R.id.username)).setText(String.format("Username: %s", detail.getUsername()));
        ((TextView)view.findViewById(R.id.password)).setText(String.format("Password: %s", detail.getPassword()));
        ((TextView)view.findViewById(R.id.state)).setText(String.format("State: %s", detail.getState()));
        ((TextView)view.findViewById(R.id.lga)).setText(String.format("Local Government: %s", detail.getLga()));

        view.findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent.setFragment(new Login(parent));
            }
        });
    }
}
