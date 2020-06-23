package com.example.components.menu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.components.MainActivity;
import com.example.components.R;
import com.example.components.helpers.Images;
import com.example.components.helpers.PhotosHelper;
import com.example.components.models.Peliculas;

import java.util.ArrayList;
import java.util.Objects;


public class RecyclerViewFragment extends Fragment {
    private RecyclerView recyclerView;
    private Peliculas looper;
    private Peliculas malefica;
    private Peliculas libroEli;
    private Peliculas theAvengers;
    private ArrayList<Peliculas> listaPeliculas;
    private Context context;

    public static RecyclerViewFragment newInstance(){
        return new RecyclerViewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view,container,false);
        context = getActivity();
        recyclerView = view.findViewById(R.id.recyclerviewpelis);
        //se inicializan los objetos Peliculas
        looper = new Peliculas(Images.looper,"Looper");
        malefica = new Peliculas(Images.malefica,"Malefica");
        libroEli = new Peliculas(Images.eli,"El libro de Eli");
        theAvengers = new Peliculas(Images.avengers,"The Avengers");
        //Se inicializa y se rellena el array que le pasaremos al Recycler.
        listaPeliculas = new ArrayList<>();
        listaPeliculas.add(looper);
        listaPeliculas.add(malefica);
        listaPeliculas.add(libroEli);
        listaPeliculas.add(theAvengers);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Se inicializa el recycler con su clase adapter.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new RecyclerViewFragment.PeliAdapter(listaPeliculas);
        recyclerView.setAdapter(adapter);
    }

    public class PeliAdapter extends RecyclerView.Adapter<RecyclerViewFragment.PeliAdapter.InViewHolder> {
        private ArrayList<Peliculas> list;

        public PeliAdapter(ArrayList<Peliculas> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public RecyclerViewFragment.PeliAdapter.InViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_button, parent, false);
            RecyclerViewFragment.PeliAdapter.InViewHolder viewHolder = new RecyclerViewFragment.PeliAdapter.InViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewFragment.PeliAdapter.InViewHolder holder, int position) {
            byte[] decodedString = PhotosHelper.convertBase64ToBytes(list.get(position).getImage());
            Bitmap unitImage = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            Drawable top = new BitmapDrawable(getResources(), unitImage);
            holder.botonPeliculas.setCompoundDrawablesWithIntrinsicBounds(null, top, null, null);
            holder.botonPeliculas.setText(list.get(position).getNombre());
            holder.pelicula = getItem(position);
        }

        @Override
        public int getItemCount() {
            return this.list.size();
        }

        private Peliculas getItem(int position) {
            return this.list.get(position);
        }

        class InViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            Button botonPeliculas;
            Peliculas pelicula;

            InViewHolder(View itemView) {
                super(itemView);
                botonPeliculas = itemView.findViewById(R.id.buttonRecycler);
                botonPeliculas.setOnClickListener(this);

            }

            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.buttonRecycler: {
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.main,DetailRecyclerFragment.newInstance(pelicula));
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                    }
                }
            }
        }
    }
}
