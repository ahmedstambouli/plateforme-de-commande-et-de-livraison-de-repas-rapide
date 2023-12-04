package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.models.Categorie;

import java.util.ArrayList;

public class home extends AppCompatActivity {
private RecyclerView.Adapter adapter;
private RecyclerView categorie;
private RecyclerView part;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (!ConnectivityUtils.isConnectedToInternet(this)) {
            Intent intent = new Intent(this, noInternet.class);
            startActivity(intent);

        }
        RecycleViewCat();

    }
    private void RecycleViewCat(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        categorie=findViewById(R.id.recycle);
        part=findViewById(R.id.recycle2);
        categorie.setLayoutManager(linearLayoutManager);
        ArrayList<Categorie> categ=new ArrayList<>();
        categ.add(new Categorie("pizza","cat_1"));
        categ.add(new Categorie("burger","cat_2"));
        categ.add(new Categorie("mlewi","cat_3"));
        categ.add(new Categorie("drink","cat_4"));


    }
}