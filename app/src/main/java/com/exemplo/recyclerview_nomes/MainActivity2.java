package com.exemplo.recyclerview_nomes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Tela de cadastro do nome
 */

public class MainActivity2 extends AppCompatActivity {

    private TextView editTextNome;
    private int posicao = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Associa os componentes aos atributos
        editTextNome = findViewById(R.id.editTextNome);

        //Recupera o intent
        Bundle extras = getIntent().getExtras();
        if (extras != null  && getIntent().hasExtra("nome")) {
            //Recupera o valor
            String valor = extras.getString("nome");
            //Recupera a posição da alteração na lista
            posicao = extras.getInt("posicao");
            //Coloca o valor na caixa de texto.
            editTextNome.setText(valor);
        } else {
            editTextNome.setText("");
        }
    }

    public void onClickBotaoSalvar(View v) {
        //Retorna os dados do cliente
        Intent data = new Intent();
        Bundle parms = new Bundle();
        //Se o id do cliente é vazio retorna cancelado
        if (editTextNome.getText().toString().equals("")) {
            setResult(RESULT_CANCELED);
        } else {
            //Dados preenchido
            parms.putInt("posicao", posicao);
            parms.putString("nome", editTextNome.getText().toString());
            data.putExtras(parms);
            setResult(RESULT_OK, data);
        }
        //Fecha a janela
        finish();
    }

    public void onClickBotaoVoltar(View v) {
        setResult(RESULT_CANCELED);
        finish();
    }
}