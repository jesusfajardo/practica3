package com.example.myapplication;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    Context context;
    List<Uri>ListImagenes;
    LayoutInflater layoutInflater;

    public GridViewAdapter(Context context, List<Uri> listImagenes) {
        this.context = context;
        this.ListImagenes = listImagenes;
    }

    @Override
    public int getCount() {
        return ListImagenes.size();
    }

    @Override
    public Object getItem(int i) {
        return ListImagenes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if(view==null){
            layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view =layoutInflater.inflate(R.layout.item_gv_imagenes,null);
        }
        ImageView ivImagen=view.findViewById(R.id.IvImagen);
        ImageView ibteliminar=view.findViewById(R.id.btn_eliminar);
        ivImagen.setImageURI(ListImagenes.get(i));
        ibteliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListImagenes.remove(i);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
