package com.example.levon.departments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    private Button signInButton;
    private Button createAccountButton;

    public LoginFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login, container, false);
        initViews(view);
        setCreateAccountButtonAction();
        return view;
    }

    private void initViews(final View view) {
        signInButton = view.findViewById(R.id.sign_in_button);
        createAccountButton = view.findViewById(R.id.create_account_button);
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
}
