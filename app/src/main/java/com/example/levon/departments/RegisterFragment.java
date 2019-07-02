package com.example.levon.departments;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.levon.departments.Models.UserModel;
import com.example.levon.departments.NetworkHelpers.NetworkRequest;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements Disableable {

    private Button registerAccountButton;
    private Button goToSignInButton;
    private TextInputEditText emailEditText;
    private TextInputEditText loginEditText;
    private TextInputEditText passwordEditText;

    private static final String EMPTY = "";

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_register, container, false);
        ((LoginActivity) getActivity()).splashTitleText.setText(R.string.registration);
        initViews(view);
        setGoToSignInButtonAction();
        setRegisterAccountButtonAction();
        return view;
    }

    @Override
    public void disable() {
        goToSignInButton.setEnabled(false);
        registerAccountButton.setEnabled(false);
        emailEditText.setEnabled(false);
        loginEditText.setEnabled(false);
        passwordEditText.setEnabled(false);
    }

    @Override
    public void enable() {
        goToSignInButton.setEnabled(true);
        registerAccountButton.setEnabled(true);
        emailEditText.setEnabled(true);
        loginEditText.setEnabled(true);
        passwordEditText.setEnabled(true);
    }

    private void initViews(final View view) {
        goToSignInButton = view.findViewById(R.id.go_to_sign_in_button);
        registerAccountButton = view.findViewById(R.id.register_account_button);
        emailEditText = view.findViewById(R.id.register_email);
        loginEditText = view.findViewById(R.id.register_login);
        passwordEditText = view.findViewById(R.id.register_password);
    }

    private void setRegisterAccountButtonAction() {
        registerAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkRequest.sharedInstance().
                        startRegister(((LoginActivity)getActivity()),
                                createUserModel());
            }
        });
    }

    private void setGoToSignInButtonAction() {
        goToSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().
                        beginTransaction().
                        replace(R.id.login_fragment_container, new LoginFragment()).commit();
            }
        });
    }

    private UserModel createUserModel() {
        UserModel userModel = new UserModel();
        userModel.setActivated(true);
        userModel.setAuthorities(new ArrayList<String>());
        userModel.setCreatedBy(EMPTY);
        userModel.setCreatedDate(EMPTY);
        userModel.setEmail(emailEditText.getText().toString());
        userModel.setFirstName(EMPTY);
        userModel.setId(0);
        userModel.setImageUrl(EMPTY);
        userModel.setLangKey(EMPTY);
        userModel.setLastModifiedBy(EMPTY);
        userModel.setLastModifiedDate(EMPTY);
        userModel.setLastName(EMPTY);
        userModel.setLogin(loginEditText.getText().toString());
        userModel.setPassword(passwordEditText.getText().toString());
        return userModel;
    }
}
