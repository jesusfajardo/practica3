package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int PICK_IMAGE =100;
    Uri imageuri ;
    Button buttongalery;
    GridView gridView;
    List<Uri> listaImagenes=new ArrayList<>();
    GridViewAdapter baseadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttongalery=findViewById(R.id.buton_galeria);
        gridView=findViewById(R.id.gv_imagenes);
        buttongalery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirGaleria();
            }
        });
    }

    private void abrirGaleria() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "SELECCIONA LAS IMAGENES "),PICK_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ClipData clipData=data.getClipData();
        if(resultCode==RESULT_OK && requestCode==PICK_IMAGE){
            if (clipData==null){
                imageuri= data.getData();
                listaImagenes.add(imageuri);
            }
        }else{
            for (int i =0;i<clipData.getItemCount();i++){
                listaImagenes.add(clipData.getItemAt(i).getUri());
            }
        }
        baseadapter=new GridViewAdapter(MainActivity.this,listaImagenes);
        gridView.setAdapter(baseadapter);
    }
}
























