package com.example.desarrollador.memoriaalpha;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class InterfazJuegoFragment extends Fragment implements Runnable{

    private ImageView profile_pic = null;
    private TextView tv = null;
    private Button salir = null;
    private Profile profile = null;

    private TextView puntaje_p = null;
    private TextView tiempo_p = null;

    private int puntaje;
    private int tiempo;

    private ImageButton img1;
    private ImageButton img2;
    private ImageButton img3;
    private ImageButton img4;
    private ImageButton img5;
    private ImageButton img6;

    private int valores[] = {1,2,3,4,5,6};
    private ImageButton images[];

    private int valorSelect = -1;
    private int valorBorrar = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.interfaz_juego, container, false);
        profile_pic = (ImageView) view.findViewById(R.id.profile);
        tv = (TextView) view.findViewById(R.id.name);
        salir = (Button) view.findViewById(R.id.salir5);

        puntaje_p = (TextView) view.findViewById(R.id.puntaje_e);
        tiempo_p = (TextView) view.findViewById(R.id.tiempo_e);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        puntaje = 0;
        iniciarImagenes();
        images = new ImageButton[]{img1,img2,img3,img3,img4,img5,img6};
        desordenarImagenes();

        agregarEventos();

        Bundle bundle = getArguments();

        if (bundle != null) {
            profile = (Profile) bundle.getParcelable(LoginFragment.PARCEL_KEY);
        } else {
            profile = Profile.getCurrentProfile();
        }


        tv.setText(profile.getName());

        Picasso.with(getActivity())
                .load(profile.getProfilePictureUri(200, 200).toString())
                .into(profile_pic);

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

    }

    private Handler puente = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bitmap bpm = BitmapFactory.decodeResource(getResources(), R.drawable.alfa);
            images[valorSelect].setImageBitmap(bpm);
            valorSelect = -1;
            images[valorBorrar].setImageBitmap(bpm);
        }
    };

    private void desordenarImagenes(){
        Random rdn = new Random();
        int aux;
        int indiceaux;

        for(int i=0; i<valores.length;i++){
            aux = valores[i];
            indiceaux = rdn.nextInt(6);

            valores[i] = valores[indiceaux];
            valores[indiceaux] = aux;
        }
    }

    private void controlador(int opcion, ImageButton img){
        Bitmap bmp = null;
        opcion--;
        switch (valores[opcion]){
            case 1:
                bmp = BitmapFactory.decodeResource(getResources(),R.drawable.img1);
                break;
            case 2:
                bmp = BitmapFactory.decodeResource(getResources(),R.drawable.img2);
                break;
            case 3:
                bmp = BitmapFactory.decodeResource(getResources(),R.drawable.img3);
                break;
            case 4:
                bmp = BitmapFactory.decodeResource(getResources(),R.drawable.img4);
                break;
            case 5:
                bmp = BitmapFactory.decodeResource(getResources(),R.drawable.img5);
                break;
            case 6:
                bmp = BitmapFactory.decodeResource(getResources(),R.drawable.img6);
                break;
        }

        if (valorSelect == -1){
            valorSelect = opcion;
            img.setImageBitmap(bmp);
        }
        else {
            if (valores[valorSelect] == valores[opcion]){
                puntaje += 10;
                puntaje_p.setText(puntaje + "");

                img.setImageBitmap(bmp);
                valorSelect = -1;
            }
        }
    }

    private void iniciarImagenes(){
        img1 = (ImageButton) getView().findViewById(R.id.carta1);
        img1.setVisibility(View.VISIBLE);
        img2 = (ImageButton) getView().findViewById(R.id.carta2);
        //img2.setVisibility(View.VISIBLE);
        img3 = (ImageButton) getView().findViewById(R.id.carta3);
        img3.setVisibility(View.VISIBLE);
        img4 = (ImageButton) getView().findViewById(R.id.carta4);
        //img4.setVisibility(View.VISIBLE);
        img5 = (ImageButton) getView().findViewById(R.id.carta5);
        //img5.setVisibility(View.VISIBLE);
        img6 = (ImageButton) getView().findViewById(R.id.carta6);
        img6.setVisibility(View.VISIBLE);

        //img1.setI
        //https://danielme.com/tip-android-31-image-slider-con-imageswitcher/
        //https://www.google.com.pe/search?espv=2&biw=1366&bih=667&q=image+slider+android&sa=X&ved=0ahUKEwi559WwntnOAhWB1x4KHRQyBTgQ1QIIXSgA
    }

    private void iniciaAlgunasImagenes(){
        int a, b, c, d, e;
        Random r = new Random();
        a = (int) (r.nextDouble()*6 + 1);
        b = (int) (r.nextDouble()*6 + 1);
        c = (int) (r.nextDouble()*6 + 1);

        images[a] = (ImageButton) getView().findViewById(R.id.carta1);
        images[a].setVisibility(View.VISIBLE);
        images[b] = (ImageButton) getView().findViewById(R.id.carta3);
        images[b].setVisibility(View.VISIBLE);
        images[c] = (ImageButton) getView().findViewById(R.id.carta5);
        images[c].setVisibility(View.VISIBLE);
    }

    private void agregarEventos(){
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.nombreImagen);
                //Bitmap bmp= BitmapFactory.decodeResource(getResources(),R.drawable.carta1);
                controlador(1,img1);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(2, img2);
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(3, img3);
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(4, img4);
            }
        });

        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(5, img5);
            }
        });

        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador(6, img6);
            }
        });
    }

    private void logout() {
        LoginManager.getInstance().logOut();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(R.id.mainContainer, new LoginFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void run(){
        SystemClock.sleep(1000);
        Message msg = new Message();
        msg.obj = 6;
        puente.sendMessage(msg);

    }
}
