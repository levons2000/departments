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
public class RegisterFragment extends Fragment {

    private Button registerAccountButton;
    private Button goToSignInButton;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_register, container, false);
        initViews(view);
        setGoToSignInButtonAction();
        return view;
    }

    private void initViews(final View view) {
        goToSignInButton = view.findViewById(R.id.go_to_sign_in_button);
        registerAccountButton = view.findViewById(R.id.create_account_button);
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

}
