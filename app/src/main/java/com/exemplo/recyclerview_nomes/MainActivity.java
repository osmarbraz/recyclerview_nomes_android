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

        // Associa os componentes da interface as propriedades
        botaoAdicionar = findViewById(R.id.buttonAdicionar);
        buttonFechar = findViewById(R.id.buttonFechar);

        // Dados para preencher o RecyclerView
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
        // Instancia o Adaptador de Cliente
        adapter = new RecyclerViewAdapterNomes(this, listaNomes);
        // Seta o Listener do adaptador
        adapter.setClickListener(this);
        // Seta o Adaptador do Recycler View
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, int position) {
        // Recupera o nome da posição
        String nome = adapter.getItem(position);
        // Mostra a mensagem do nome selecionado
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
     * @param intent Dados do intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // Executa no retorno das telas
        super.onActivityResult(requestCode, resultCode, intent);
        // Se o retorno foi Ok
        if (resultCode == RESULT_OK) {
            // Verifica se os dados foram preenchidos
            if  (intent.hasExtra("nome")) {
                int posicao = intent.getExtras().getInt("posicao");
                String nome = intent.getExtras().getString("nome");
                // Se é um novo valor
                if (posicao == -1) {
                    // Adiciona os dados na lista
                    adapter.adicionarNome(nome);
                }else {
                    // Altera os dados na lista
                    adapter.alterarNome(posicao, nome);
                }
            }
        }
    }

    /**
     * Evento do botão fechar
     * @param v
     */
    public void onclickFechar(View v){

        System.exit(0);
    }
}