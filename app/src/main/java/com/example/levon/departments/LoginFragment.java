package com.example.levon.departments;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.levon.departments.Models.UserLoginModel;
import com.example.levon.departments.NetworkHelpers.NetworkRequest;
import com.google.gson.Gson;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements Disableable {


    private Button signInButton;
    private Button createAccountButton;
    private TextInputEditText loginEditText;
    private TextInputEditText passwordEditText;

    public LoginFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login, container, false);
        ((LoginActivity) getActivity()).splashTitleText.setText(R.string.sign_in);
        initViews(view);
        setCreateAccountButtonAction();
        setSignInButtonAction();
        return view;
    }

    @Override
    public void disable() {
        signInButton.setEnabled(false);
        createAccountButton.setEnabled(false);
        loginEditText.setEnabled(false);
        passwordEditText.setEnabled(false);
    }

    @Override
    public void enable() {
        signInButton.setEnabled(true);
        createAccountButton.setEnabled(true);
        loginEditText.setEnabled(true);
        passwordEditText.setEnabled(true);
    }

    private void initViews(final View view) {
        signInButton = view.findViewById(R.id.sign_in_button);
        createAccountButton = view.findViewById(R.id.create_account_button);
        loginEditText = view.findViewById(R.id.sign_in_login);
        passwordEditText = view.findViewById(R.id.sign_in_password);
    }

    private void setSignInButtonAction() {
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetworkRequest.sharedInstance().
                        makeLoginRequest(((LoginActivity) getActivity()),
                                createUserLoginModel());
            }
        });
    }

    private void setCreateAccountButtonAction() {
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().
                        beginTransaction().
                        replace(R.id.login_fragment_container, new RegisterFragment()).commit();
            }
        });
    }

    private UserLoginModel createUserLoginModel() {
        final UserLoginModel userLoginModel = new UserLoginModel();
        userLoginModel.setUsername(loginEditText.getText().toString());
        userLoginModel.setRememberMe(true);
        userLoginModel.setPassword(passwordEditText.getText().toString());
        Gson gson = new Gson();
        Log.d("json", gson.toJson(userLoginModel));
        return userLoginModel;
    }
}
