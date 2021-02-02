package com.bsoft.signupprototype.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bsoft.signupprototype.Main;
import com.bsoft.signupprototype.R;
import com.bsoft.signupprototype.fragments.LoginFragments.LoginUID;
import com.bsoft.signupprototype.item.Detail;
import com.bsoft.signupprototype.services.SignupService;
import com.bsoft.signupprototype.utils.Util;

public class Signup extends BaseFragmet implements View.OnClickListener, SignupService.ServiceListener {
    AutoCompleteTextView name, surname, email, username, password, repassword, state, lga, number;
    Detail detail;
    boolean isEmailValid = false, isPasswordValid = false;

    public Signup(Main parent) {
        super(parent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.submit).setOnClickListener(this);
        view.findViewById(R.id.login).setOnClickListener(this);

        ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(this.requireContext(), androidx.appcompat.R.layout.select_dialog_item_material, Util.States);
        state = (AutoCompleteTextView)view.findViewById(R.id.state);
        state.setAdapter(stateAdapter);
        state.setTag("State");

        lga = view.findViewById(R.id.lga);
        lga.setTag("Local Government");
        lga.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                if(focused && !state.getText().toString().isEmpty()){
                    String initState = state.getText().toString();
                    ArrayAdapter<String> lgaAdapter = new ArrayAdapter<String>(requireContext(), androidx.appcompat.R.layout.select_dialog_item_material, Util.GetLGA(initState));
                    lga.setAdapter(lgaAdapter);
                }
            }
        });

        name = view.findViewById(R.id.name);
        name.setTag("Name");

        surname = view.findViewById(R.id.surname);
        surname.setTag("Surname");

        email = view.findViewById(R.id.email);
        email.setTag("Email");
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                if(Util.isEmail(editable.toString())){
                    setOk(view, R.id.email_status);
                    isEmailValid = true;
                }else {
                    setBad(view, R.id.email_status);
                    isEmailValid = false;
                }
            }
        });

        Util.setFontAwsomeWidthId(requireContext(), view, R.id.password_status, R.id.repassword_status, R.id.email_status);
        password = view.findViewById(R.id.password);
        password.setTag("Password");
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length() >= 7){
                    setOk(view, R.id.password_status);
                    isPasswordValid = true;
                }else{
                    setBad(view, R.id.password_status);
                    isPasswordValid = false;
                }

                if(repassword.getText().toString().equals(editable.toString())){
                    setOk(view, R.id.repassword_status);
                }else{
                    setBad(view, R.id.repassword_status);
                }
            }
        });

        repassword = view.findViewById(R.id.repassword);
        repassword.setTag("Repassword");
        repassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                if(password.getText().toString().equals(editable.toString())){
                    setOk(view, R.id.repassword_status);
                }else{
                    setBad(view, R.id.repassword_status);
                }
            }
        });

        username = view.findViewById(R.id.username);
        username.setTag("Username");

        number = view.findViewById(R.id.number);
        number.setTag("Number");
    }

    private void setOk(View rootView, int id){
        TextView view = rootView.findViewById(id);
        view.setText(R.string.fa_check);
        view.setTextColor(rootView.getResources().getColor(R.color.colorPrimary));
    }

    private void setBad(View rootView, int id){
        TextView view = rootView.findViewById(id);
        view.setText(R.string.fa_close);
        view.setTextColor(rootView.getResources().getColor(R.color.red));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                parent.setFragment(new Login(parent));
                break;
            case R.id.submit:
                if(Util.isFormValid(requireContext(), name, surname, email, number, state, lga, username, password, repassword)){
                    if(!isEmailValid){
                        Toast.makeText(requireContext(), "Email is Invalid", Toast.LENGTH_LONG).show();
                    }else if(!isPasswordValid){
                        Toast.makeText(requireContext(), "Password is Invalid", Toast.LENGTH_LONG).show();
                    }else if(!password.getText().toString().equals(repassword.getText().toString())){
                        Toast.makeText(requireContext(), "Password Mismatch", Toast.LENGTH_LONG).show();
                    }else{
                        register();
                    }
                }
                break;
        }
    }

    @Override
    public void onServiceStart() {
        parent.setLoading("Sigup in progress, please wait");
    }

    @Override
    public void onServiceEnd(String report) {
        parent.clearLoading();
        if(report.equals("Signup Successful")){
            parent.setCredential(detail.getCredential());
            parent.saveDetails();
            parent.setFragment(new Success(parent, detail));
        }
    }

    public void register(){
        String uid = Util.generateUid(username.getText().toString(), state.getText().toString());
        detail = new Detail(uid, username.getText().toString(), password.getText().toString());
        detail.setEmail(email.getText().toString());
        detail.setState(state.getText().toString());
        detail.setLga(lga.getText().toString());
        detail.setNumber(number.getText().toString());
        detail.setSurname(surname.getText().toString());
        detail.setName(name.getText().toString());
        new SignupService(this, detail).execute();
    }
}
