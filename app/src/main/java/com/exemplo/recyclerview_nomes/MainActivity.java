package com.exemplo.recyclerview_nomes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Tela principal.
 */

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapterNomes.ItemClickListener {

    private Button botaoAdicionar;
    private Button buttonFechar;
    private RecyclerViewAdapterNomes adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Associa os componentes da interface as propriedades
        botaoAdicionar = findViewById(R.id.buttonAdicionar);
        buttonFechar = findViewById(R.id.buttonFechar);

        //dados para preencher o RecyclerView com
        ArrayList<String> listaNomes = new ArrayList<>();
        // Adiciona nomes
        listaNomes.add("Bruno");
        listaNomes.add("Carlos");
        listaNomes.add("Pedro");
        listaNomes.add("João");
        listaNomes.add("Zé");

        // Configura o RecyclerView
        recyclerView = findViewById(R.id.recyclerViewNomes);
        // Visualização em lista
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapterNomes(this, listaNomes);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, int position) {
        String nome = adapter.getItem(position);
        Toast.makeText(this, "Clique no nome: " + nome + " linha número: " + position, Toast.LENGTH_SHORT).show();
    }

    /**
     * Evento do botão adicionar nome
     * @param v
     */
    public void onClickBotaoAdicionar(View v){
        // Recupera o intennt para a tela2
        Intent intent = new Intent(this, MainActivity2.class);
        // Abre a segunda tela
        startActivityForResult(intent, 0);
    }

    /**
     * Verifica o resultado do retorno da chamada de um activity.
     * @param requestCode Código da requisição
     * @param resultCode Código de retorno
     * @param data Dados do intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Executa no retorno das telas
        super.onActivityResult(requestCode, resultCode, data);
        //Se o retorno foi Ok
        if (resultCode == RESULT_OK) {
            //Verifica se os dados foram preenchidos
            if  (data.hasExtra("nome")) {
                int posicao = data.getExtras().getInt("posicao");
                String nome = data.getExtras().getString("nome");
                // Valor novo
                if (posicao == -1) {
                    //Adiciona os dados na lista
                    adapter.adicionarNome(nome);
                }else {
                    //Altera os dados na lista
                    adapter.alterarNome(posicao, nome);
                }
            }
        }
    }

    public void onclickFechar(View v){
        System.exit(0);
    }
}