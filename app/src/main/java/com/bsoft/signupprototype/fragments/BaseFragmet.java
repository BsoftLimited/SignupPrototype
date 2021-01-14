package com.bsoft.signupprototype.fragments;

import androidx.fragment.app.Fragment;

import com.bsoft.signupprototype.Main;

public abstract class BaseFragmet extends Fragment {
    protected Main parent;

    protected BaseFragmet(Main parent){
        this.parent = parent;
    }
}
