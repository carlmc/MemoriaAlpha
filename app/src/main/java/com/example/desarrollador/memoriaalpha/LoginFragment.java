package com.example.desarrollador.memoriaalpha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;


public class LoginFragment extends Fragment{
    private Button ingresar;
    private LoginButton facebook;
    private CallbackManager callbackManager;

    private AccessTokenTracker mtracker = null;
    private ProfileTracker mprofileTracker = null;

    public static final String PARCEL_KEY = "parcel_key";

    FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {

            Profile profile = Profile.getCurrentProfile();
            interfaz(profile);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FacebookSdk.sdkInitialize(getApplicationContext());
        //AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();

        //setContentView(R.layout.login);

        mtracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

                Log.v("AccessTokenTracker", "oldAccessToken=" + oldAccessToken + "||" + "CurrentAccessToken" + currentAccessToken);
            }
        };

        //ingresar = (Button) findViewById(R.id.ingresar);
        //facebook = (LoginButton) findViewById(R.id.facebook);

        //facebook.setReadPermissions(Arrays.asList("email"));

        /*ingresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LoginFragment.this, Nivel.class);
                startActivity(intent);
                finish();
            }
        });*/

        /*facebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                goPremio();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), R.string.cancel_login, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_SHORT).show();
            }
        });*/

        mprofileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {

                Log.v("Session Tracker", "oldProfile=" + oldProfile + "||" + "currentProfile" + currentProfile);
                interfaz(currentProfile);

            }
        };

        mtracker.startTracking();
        mprofileTracker.startTracking();
    }

    private void interfaz(Profile profile){
        if (profile != null){
            Bundle mBundle = new Bundle();
            mBundle.putParcelable(PARCEL_KEY, profile);
            InterfazJuegoFragment hf = new InterfazJuegoFragment();
            hf.setArguments(mBundle);

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mainContainer, new InterfazJuegoFragment());
            fragmentTransaction.commit();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        facebook = (LoginButton) view.findViewById(R.id.facebook);
        facebook.setReadPermissions("user_friends");

        facebook.setFragment(this);
        facebook.registerCallback(callbackManager, callback);

        ingresar = (Button) view.findViewById(R.id.ingresar);
        ingresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent(getActivity(),InterfazJuegoFragment.class);
                startActivity(intent);

            }
        });
    }

    public void ingresajuego(){
        Intent intent = new Intent(getActivity().getApplication(),InterfazJuegoFragment.class);
        startActivity(intent);
    }

    /*private void goPremio(){
        Intent intent = new Intent(LoginFragment.this, Premio.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStop() {
        super.onStop();
        mtracker.stopTracking();
        mprofileTracker.stopTracking();
    }

    public boolean isLoggedIn(){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(isLoggedIn()){
            facebook.setVisibility(View.INVISIBLE);
            Profile profile = Profile.getCurrentProfile();
            interfaz(profile);
        }
    }
}
