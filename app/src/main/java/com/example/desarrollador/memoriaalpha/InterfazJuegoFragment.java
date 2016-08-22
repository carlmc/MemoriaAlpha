package com.example.desarrollador.memoriaalpha;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.Profile;
import com.squareup.picasso.Picasso;

public class InterfazJuegoFragment extends Fragment{

    private ImageView profile_pic = null;
    private TextView tv = null;
    private Button logoutButton = null;
    private Profile profile = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.interfaz_juego, container, false);
        profile_pic = (ImageView) view.findViewById(R.id.profile);
        tv = (TextView) view.findViewById(R.id.name);
        //logoutButton = (Button) view.findViewById(R.id.logout);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();

        if (bundle != null) {
            profile = (Profile) bundle.getParcelable(LoginFragment.PARCEL_KEY);
        } else {
            profile = Profile.getCurrentProfile();
        }


        tv.setText(profile.getName());

        Picasso.with(getActivity())
                .load(profile.getProfilePictureUri(300, 300).toString())
                .into(profile_pic);

        /*logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });*/

    }


}
