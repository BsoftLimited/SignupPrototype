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

        TextView uid = view.findViewById(R.id.uid);
        uid.setText(String.format("UID: %s", detail.getUid()));

        TextView name = view.findViewById(R.id.name);
        name.setText(String.format("Name: %s", detail.getName()));

        TextView surname = view.findViewById(R.id.surname);
        surname.setText(String.format("Surname: %s", detail.getSurname()));

        TextView email = view.findViewById(R.id.email);
        email.setText(String.format("Email: %s", detail.getEmail()));

        TextView username = view.findViewById(R.id.username);
        username.setText(String.format("Username: %s", detail.getUsername()));

        view.findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent.setFragment(new Login(parent));
            }
        });
    }
}
