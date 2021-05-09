package com.exemplo.recyclerview_nomes;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapterNomes.ItemClickListener {
    //https://medium.com/@brunoqualhato/android-como-criar-um-adapter-com-java-e-recyclerview-a2db50b9a96e
    private RecyclerViewAdapterNomes adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dados para preencher o RecyclerView com
        ArrayList<String> listaNomes = new ArrayList<>();
        // Adiciona nomes
        listaNomes.add("Bruno");
        listaNomes.add("Carlos");
        listaNomes.add("Pedro");
        listaNomes.add("João");
        listaNomes.add("Zé");

        // Configura o RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rv_nomes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Visualização em lista
        adapter = new RecyclerViewAdapterNomes(this, listaNomes);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "Clique no nome: " + adapter.getItem(position) + " linha numero: " + position, Toast.LENGTH_SHORT).show();
    }
}