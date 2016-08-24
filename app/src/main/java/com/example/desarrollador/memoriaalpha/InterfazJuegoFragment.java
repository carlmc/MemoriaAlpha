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
import android.widget.Toast;

import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class InterfazJuegoFragment extends Fragment implements Runnable{

    private ImageView profile_pic = null;
    private TextView tv = null;
    private Button salir = null;
    private Profile profile = null;

    private TextView puntaje_p = null;
    private TextView tiempo_p = null;

    private int puntaje;
    private int tiempo;

    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;
    private ImageView img6;

    private int valores[] = {1,2,3,4,5,6};
    private ImageView images[];

    private int galeria[] = { R.drawable.img1, R.drawable.img2, R.drawable.img3,
                                R.drawable.img4, R.drawable.img5, R.drawable.img6};

    private static final Integer duracion = 2500;
    private Timer timer;
    private int posicion;

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
        //desordenarImagenes();

        //agregarEventos();

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

    /*private void desordenarImagenes(){
        Random rdn = new Random();
        int aux;
        int indiceaux;

        for(int i=0; i<valores.length;i++){
            aux = valores[i];
            indiceaux = rdn.nextInt(6);

            valores[i] = valores[indiceaux];
            valores[indiceaux] = aux;
        }
    }*/

    private void controlador(int opcion, ImageView img){
        Bitmap bmp = null;
        //opcion--;
        switch (opcion){
            case 1:
                //bmp = BitmapFactory.decodeResource(getResources(),R.drawable.img1);
                break;
            case 2:
                //bmp = BitmapFactory.decodeResource(getResources(),R.drawable.img2);
                break;
            case 3:
                //bmp = BitmapFactory.decodeResource(getResources(),R.drawable.img3);
                break;
            case 4:
                //bmp = BitmapFactory.decodeResource(getResources(),R.drawable.img4);
                break;
            case 5:
                //bmp = BitmapFactory.decodeResource(getResources(),R.drawable.img5);
                break;
            case 6:
                //bmp = BitmapFactory.decodeResource(getResources(),R.drawable.img6);
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
        }

        if (valores[valorSelect] == valores[opcion]){
            puntaje += 10;
            puntaje_p.setText(puntaje + "");

            //img.setImageBitmap(bmp);
            //valorSelect = -1;
        }

    }

    public void iniciarImagenes(){

        img1 = (ImageView) getView().findViewById(R.id.carta1);
        img2 = (ImageView) getView().findViewById(R.id.carta2);
        img3 = (ImageView) getView().findViewById(R.id.carta3);
        img4 = (ImageView) getView().findViewById(R.id.carta4);
        img5 = (ImageView) getView().findViewById(R.id.carta5);
        img6 = (ImageView) getView().findViewById(R.id.carta6);

        images = new ImageView[]{img1,img2,img3,img4,img5,img6};

        Random r = new Random();
        final int a;
        a = (int)(r.nextDouble()*10+1);

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int i=0;

            int d=1;

            public void run() {
                if(i==0) {
                    iniciaAlgunos(a);
                }
                i++;

                handler.postDelayed(this, 2500);
                //for interval...

                agregarEventos(galeria);

                if(i==2) {
                    int j;
                    for (j=0;j<images.length;j++){
                        images[j].setImageResource(galeria[j]);
                    }

                }


            }

        };
        handler.postDelayed(runnable, 2500);


    }

    private void iniciaAlgunos(int opcion){

        switch (opcion){
            case 1:
                images[0].setImageResource(galeria[0]);
                images[3].setImageResource(galeria[3]);
                images[5].setImageResource(galeria[5]);

                break;
            case 2:
                images[1].setImageResource(galeria[1]);
                images[2].setImageResource(galeria[2]);
                images[5].setImageResource(galeria[5]);
                break;
            case 3:
                images[0].setImageResource(galeria[0]);
                images[1].setImageResource(galeria[1]);
                images[4].setImageResource(galeria[4]);
                break;
            case 4:
                images[2].setImageResource(galeria[2]);
                images[3].setImageResource(galeria[3]);
                images[5].setImageResource(galeria[5]);
                break;
            case 5:
                images[3].setImageResource(galeria[3]);
                images[4].setImageResource(galeria[4]);
                images[5].setImageResource(galeria[5]);
                break;
            case 6:
                images[0].setImageResource(galeria[0]);
                images[1].setImageResource(galeria[1]);
                images[2].setImageResource(galeria[2]);
                break;
            case 7:
                images[1].setImageResource(galeria[1]);
                images[2].setImageResource(galeria[2]);
                images[4].setImageResource(galeria[4]);
                break;
            case 8:
                images[1].setImageResource(galeria[1]);
                images[2].setImageResource(galeria[2]);
                images[3].setImageResource(galeria[3]);
                break;
            case 9:
                images[0].setImageResource(galeria[0]);
                images[2].setImageResource(galeria[2]);
                images[4].setImageResource(galeria[4]);
                break;
            case 10:
                images[0].setImageResource(galeria[0]);
                images[1].setImageResource(galeria[1]);
                images[5].setImageResource(galeria[5]);
                break;
        }

    }

    private void agregarEventos(int mat[]){

        for (int i=0;i<mat.length;i++){
            mat[i] = galeria[i];
        }



        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.nombreImagen);
                //Bitmap bmp= BitmapFactory.decodeResource(getResources(),R.drawable.carta1);
                //controlador(1,img1);
                if(v==images[0]) {
                    puntaje += 20;
                    puntaje_p.setText(puntaje + "");
                }
                Toast.makeText(getActivity(), "Clicked Image1", Toast.LENGTH_SHORT).show();

                //puntaje += 20;
                //puntaje_p.setText(puntaje+"");
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //controlador(2, img2);
                //Toast.makeText(getActivity(), "Clicked Image",Toast.LENGTH_SHORT).show();

                    puntaje += 20;
                    puntaje_p.setText(puntaje+"");

            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //controlador(3, img3);
                //Toast.makeText(getActivity(), "Clicked Image", Toast.LENGTH_SHORT).show();
                if (v.equals(img3)) {
                    puntaje += 20;
                    puntaje_p.setText(puntaje+"");
                }
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //controlador(4, img4);
                //Toast.makeText(getActivity(), "Clicked Image", Toast.LENGTH_SHORT).show();
                if (v.equals(img4)) {
                    puntaje += 20;
                    puntaje_p.setText(puntaje+"");
                }
            }
        });

        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //controlador(5, img5);
                //Toast.makeText(getActivity(), "Clicked Image", Toast.LENGTH_SHORT).show();
                if (v.equals(img5)) {
                    puntaje += 20;
                    puntaje_p.setText(puntaje+"");
                }
            }
        });

        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //controlador(6, img6);
                //Toast.makeText(getActivity(), "Clicked Image", Toast.LENGTH_SHORT).show();
                if (v.equals(img6)) {
                    puntaje += 20;
                    puntaje_p.setText(puntaje+"");
                }
                else{

                }
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
