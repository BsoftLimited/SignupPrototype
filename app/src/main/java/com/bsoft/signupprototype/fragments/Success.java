package com.bsoft.signupprototype.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bsoft.signupprototype.Main;
import com.bsoft.signupprototype.R;
import com.bsoft.signupprototype.item.Detail;

public class Success extends BaseFragmet {
    Detail detail;
    public Success(Main parent, Detail detail) {
        super(parent);
        this.detail = detail;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.success, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parent.setFragment(new Preview(parent, detail));
            }
        });
    }
}
