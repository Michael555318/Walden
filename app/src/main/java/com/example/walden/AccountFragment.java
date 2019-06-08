package com.example.walden;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import static com.example.walden.LoginActivity.client;
import static com.example.walden.LoginActivity.personEmail;
import static com.example.walden.LoginActivity.personFamilyName;
import static com.example.walden.LoginActivity.personPhoto;

public class AccountFragment extends Fragment {

    ImageView pic;
    TextView email;
    Button signout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_account, container, false);
        pic = v.findViewById(R.id.profile_pic);
        email = v.findViewById(R.id.email);
        signout = v.findViewById(R.id.button_signout);

        pic.setImageURI(personPhoto);
        email.setText(personEmail);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                client.signOut()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent signin = new Intent(v.getContext(), LoginActivity.class);
                                startActivity(signin);
                            }
                        });
            }
        });

        return v;
    }
}
