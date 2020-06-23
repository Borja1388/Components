package com.example.components.menu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.components.R;
import com.example.components.helpers.PhotosHelper;
import com.example.components.models.Peliculas;

public class DetailRecyclerFragment extends Fragment {
    private static String ARG_PARAM = "pelicula";
    private Peliculas pelicula;
    private Bundle bundle;
    private ImageView imagePeli;
    private TextView nombrePeli;

    public static DetailRecyclerFragment newInstance(Peliculas pelicula){
        DetailRecyclerFragment fragment = new DetailRecyclerFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM, pelicula);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_recycler,container,false);
        imagePeli = view.findViewById(R.id.imagePeli);
        nombrePeli = view.findViewById(R.id.nombrePeli);
        bundle = getArguments();
        if(bundle != null){
            pelicula =(Peliculas) bundle.getSerializable(ARG_PARAM);
        }
        if(pelicula != null){
            byte[] decodedString = PhotosHelper.convertBase64ToBytes(pelicula.getImage());
            Bitmap unitImage = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            Drawable drawable = new BitmapDrawable(getResources(), unitImage);
            imagePeli.setBackground(drawable);
            nombrePeli.setText(pelicula.getNombre());
        }
        return view;
    }
}
