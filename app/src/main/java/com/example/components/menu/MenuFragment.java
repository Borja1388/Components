package com.example.components.menu;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.components.R;

import java.util.Objects;

public class MenuFragment extends Fragment implements View.OnClickListener {

    private Button spinnerBtn;
    private Button listViewBtn;
    private Button recyclerView;
    private Context context;

   public static MenuFragment newInstance(){
       return new MenuFragment();
   }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu,container,false);
        context = getActivity();
        spinnerBtn = view.findViewById(R.id.spinner);
        listViewBtn = view.findViewById(R.id.listView);
        recyclerView = view.findViewById(R.id.recyclerView);
        spinnerBtn.setOnClickListener(this);
        listViewBtn.setOnClickListener(this);
        recyclerView.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.spinner:{
                createDialogSpinner();
                break;
            }
            case R.id.listView:{
                createDialogListView();
                break;
            }
            case R.id.recyclerView:{
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main,RecyclerViewFragment.newInstance());
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            }
        }
    }

    public void createDialogListView(){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_team_listview);
        Button cancelar = dialog.findViewById(R.id.cancelar);
        ListView listView = dialog.findViewById(R.id.listView);
        final ArrayAdapter<String> adaptador = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.nombres));
        listView.setAdapter(adaptador);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.create();
        dialog.show();
    }
    public void createDialogSpinner(){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_team_spi);
        Spinner spinnerNombres = dialog.findViewById(R.id.spinnernombres);
        Button cancelar = dialog.findViewById(R.id.cancelar);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.nombres));
        spinnerNombres.setAdapter(adapter);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.create();
        dialog.show();
    }
}
