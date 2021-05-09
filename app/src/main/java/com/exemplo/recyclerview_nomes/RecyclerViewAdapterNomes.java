package com.exemplo.recyclerview_nomes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterNomes extends RecyclerView.Adapter<RecyclerViewAdapterNomes.ViewHolderNome> {

    private List<String> listaNomes;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;
    private Context context;

    /**
     * construtor para passar os dados para o RecyclerView de Clientes
     * @param context Activity que chamou a classe
     * @param listaNomes Lista com os dados
     */
    RecyclerViewAdapterNomes(Context context, List<String> listaNomes) {
        this.inflater = LayoutInflater.from(context);
        this.listaNomes = listaNomes;
        this.context = context;
    }

    /**
     * Get para lista de nomes
     * @return
     */
    public List<String> getListaNomes() {
        return listaNomes;
    }

    /**
     * Set para lista de nomes
     * @param listaNomes
     */
    public void setListaNomes(ArrayList<String> listaNomes) {
        this.listaNomes = listaNomes;
    }

    /**
     * infla o layout da linha de xml quando necessário
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolderNome onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_nome_view, parent, false);
        return new ViewHolderNome(view);
    }

    /**
     * liga os dados ao TextView em cada linha
     * @param holder
     * @param posicao
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolderNome holder, int posicao) {
        String nome = listaNomes.get(posicao);
        holder.textViewNome.setText(nome);

        holder.buttonExcluir.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                removerNome(posicao);
            }
        });

        holder.buttonAlterar.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterarNomeClick(posicao, nome);
            }
        });
    }

    /**
     *  Retorna o total de linhas da lista
     * @return Um inteiro
     */
    @Override
    public int getItemCount() {
        return listaNomes.size();
    }

    /**
     * Adiciona um novo nome a lista
     * @param novo Um nome
     */
    public void adicionarNome(String novo){
        // Adiciona o item na ultima posicao
        listaNomes.add(novo);
        // Notifica as mudanças a Recycler View
        notifyDataSetChanged();
    }

    /**
     * Remove um nome da lista pela posição
     * @param posicao Posição do nome a ser excluído
     */
    public void removerNome(int posicao){
        // Remove o item na posição desejada
        listaNomes.remove(posicao);
        // Notifica as mudanças a Recycler View
        notifyDataSetChanged();
    }

    /**
     * Altera um nome da lista pela posição
     * @param posicao Posição do nome a ser alterada
     * @param novo Novo nome a ser alterado
     */
    public void alterarNomeClick(int posicao, String novo){
        Intent intent = new Intent(context, MainActivity2.class);
        // Armazena o valor no intent
        intent.putExtra("posicao",posicao);
        intent.putExtra("nome", getItem(posicao));
        // Abre a segunda tela
        ((Activity) context).startActivityForResult(intent, 0);

    }

    /**
     * Altera um nome da lista pela posição
     * @param posicao Posição do nome a ser alterado
     * @param alterado O nome para alteração
     */
    public void alterarNome(int posicao, String alterado){
        listaNomes.set(posicao, alterado); //Atualiza o item na posição desejada
        // Notifica as mudanças a Recycler View
        notifyDataSetChanged();
    }

    /**
     * Armazena e recicla as visualizações à medida que elas são deslizadas para fora da tela
     */
    public class ViewHolderNome extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewNome;
        ImageButton buttonExcluir;
        ImageButton buttonAlterar;

        /**
         * Construtor do ViewHolder de nomes.
         * @param itemView
         */
        ViewHolderNome(View itemView) {
            super(itemView);
            textViewNome = itemView.findViewById(R.id.textViewNome);
            buttonExcluir  = itemView.findViewById(R.id.buttonExcluir);
            buttonAlterar  = itemView.findViewById(R.id.buttonAlterar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onItemClick(view, getAdapterPosition());
        }
    }

    /**
     * Método de conveniência para obter dados na posição de clique
     * @param i
     * @return
     */
    String getItem(int i) {
        return listaNomes.get(i);
    }

    /**
     * Permite que os eventos de cliques sejam capturados
     * @param itemClickListener
     */
    void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    /**
     * Interface do método para responder a eventos de cliques
     */
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}