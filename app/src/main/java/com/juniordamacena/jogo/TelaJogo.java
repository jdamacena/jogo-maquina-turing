package com.juniordamacena.jogo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class TelaJogo extends AppCompatActivity {

    private static final int POSICAO_FINAL_JOGO = 11;
    private TextView[] casasTabuleiro;
    private View tabuleiro;
    private int posicaoJogador1 = 0, posicaoJogador2 = 0;
    private Jogadores jogadorDaVez;
    private String[] frasesDeIndicacaoDaVez;
    private TextView txtStatusJog2;
    private TextView txtStatusJog1;
    private TextView txtTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_jogo);

        tabuleiro = findViewById(R.id.tabuleiro);
        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        frasesDeIndicacaoDaVez = getResources().getStringArray(R.array.indicacaoDoJogadorDaVez);
        txtStatusJog1 = (TextView) findViewById(R.id.txtStatusJog1);
        txtStatusJog2 = (TextView) findViewById(R.id.txtStatusJog2);

        findViewById(R.id.btnDado).setOnClickListener(onClickBtnDado());

        configurarTabuleiro();
        mostrarDialogoDeBoasVindas();
    }

    private void atualizarTitulo(Jogadores jogador) {
        String frase = frasesDeIndicacaoDaVez[new Random().nextInt(frasesDeIndicacaoDaVez.length)];
        txtTitulo.setText(String.format(frase, jogador));
    }

    /**
     * Mostra um diálogo inicial do jogo, e também, informa quem começa
     */
    private void mostrarDialogoDeBoasVindas() {
        jogadorDaVez = escolherJogadorAleatoriamente();

        new AlertDialog.Builder(this)
                .setTitle("Bem vindo ao jogo")
                .setMessage("Quem começa é o " + jogadorDaVez)
                .setPositiveButton("Começar", null)
                .show();

        // Atualizar o título no topo da tela
        atualizarTitulo(jogadorDaVez);
    }

    /**
     * Escolhe um jogador aleatoriamente
     *
     * @return jogador
     */
    private Jogadores escolherJogadorAleatoriamente() {
        Jogadores[] tmp = {Jogadores.JOGADOR01, Jogadores.JOGADOR02};
        return tmp[new Random().nextInt(2)];
    }

    @NonNull
    private View.OnClickListener onClickBtnDado() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarJogada();
            }
        };
    }


    /**
     * Configurar o tabuleiro
     */
    public void configurarTabuleiro() {
        // Obter as instâncias das views do tabuleiro
        casasTabuleiro = new TextView[]{
                (TextView) tabuleiro.findViewById(R.id.txtInicio),
                (TextView) tabuleiro.findViewById(R.id.txt01),
                (TextView) tabuleiro.findViewById(R.id.txt02),
                (TextView) tabuleiro.findViewById(R.id.txt03),
                (TextView) tabuleiro.findViewById(R.id.txt04),
                (TextView) tabuleiro.findViewById(R.id.txt05),
                (TextView) tabuleiro.findViewById(R.id.txt06),
                (TextView) tabuleiro.findViewById(R.id.txt07),
                (TextView) tabuleiro.findViewById(R.id.txt08),
                (TextView) tabuleiro.findViewById(R.id.txt09),
                (TextView) tabuleiro.findViewById(R.id.txt10),
                (TextView) tabuleiro.findViewById(R.id.txtFinal)};
    }

    /**
     * Esse método atualiza a posição dos jogadores no tabuleiro
     */
    public void atualizarPosicao(int posicaoJogador01, int posicaoJogador02) {
        posicaoJogador01 = posicaoJogador01 <= 0 ? 0 : posicaoJogador01;
        posicaoJogador02 = posicaoJogador02 <= 0 ? 0 : posicaoJogador02;
        posicaoJogador01 = posicaoJogador01 >= casasTabuleiro.length ? casasTabuleiro.length - 1 : posicaoJogador01;
        posicaoJogador02 = posicaoJogador02 >= casasTabuleiro.length ? casasTabuleiro.length - 1 : posicaoJogador02;

        // Limpar todos os drawables já existentes no tabuleiro
        for (TextView view : casasTabuleiro) {
            view.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }

        // Levar os jogadores para as devida casas
        casasTabuleiro[posicaoJogador01].setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.ic_jogador_01, 0, 0);
        casasTabuleiro[posicaoJogador02].setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.mipmap.ic_jogador_02);
    }

    public void realizarJogada() {
        // Obter o resultado do dado
        int[] dado = {-1, 1, 2};
        int resultadoDoDado = dado[new Random().nextInt(3)];
        Log.i("Dado", "resultado=" + resultadoDoDado);

        // Ajustar a posição dos jogadores
        switch (jogadorDaVez) {

            case JOGADOR01:

                // Atualizar posição jogador 1
                posicaoJogador1 += resultadoDoDado;

                // Evitar que posição seja menor que 0
                posicaoJogador1 = posicaoJogador1 < 0 ? 0 : posicaoJogador1;

                // Caso o jogo tenha acabado, informar o vencedor
                if (posicaoJogador1 >= POSICAO_FINAL_JOGO)
                    finalDeJogo(Jogadores.JOGADOR01);
                else
                    jogadorDaVez = Jogadores.JOGADOR02;
                break;

            case JOGADOR02:

                // Atualizar posição jogador 2
                posicaoJogador2 += resultadoDoDado;

                // Evitar que posição seja menor que 0
                posicaoJogador2 = posicaoJogador2 < 0 ? 0 : posicaoJogador2;

                // Caso o jogo tenha acabado, informar o vencedor
                if (posicaoJogador2 >= POSICAO_FINAL_JOGO)
                    finalDeJogo(Jogadores.JOGADOR02);
                else
                    jogadorDaVez = Jogadores.JOGADOR01;
                break;
        }
        atualizarPosicao(posicaoJogador1, posicaoJogador2);
        atualizarTitulo(jogadorDaVez);
        atualizarStatus(jogadorDaVez, resultadoDoDado);
    }

    /**
     * Configurar os textos de acordo com o número de casas que o jogador andou
     *
     * @param jogadorDaVez  o jogador que terá o status atualizado
     * @param casasQueMoveu o número de casas que o mesmo se moveu
     */
    private void atualizarStatus(Jogadores jogadorDaVez, int casasQueMoveu) {
        TextView view = (jogadorDaVez == Jogadores.JOGADOR01) ? txtStatusJog1 : txtStatusJog2;

        switch (casasQueMoveu) {
            case -1:
                view.setText(R.string.statusVoltou1Casa);
            case 1:
                view.setText(R.string.statusAndou1Casa);
            case 2:
                view.setText(R.string.statusAndou2Casas);
        }
    }

    /**
     * Finalizar o jogo e informar o vencedor
     */
    private void finalDeJogo(Jogadores vencedor) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialogoGameOverTitulo)
                .setMessage(String.format(getString(R.string.dialogoGameOverMensagem), vencedor))
                .setNegativeButton(R.string.dialogoGameOverLabelBtnFechar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setPositiveButton(R.string.dialogoGameOverLabelBtnReiniciar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        reiniciarJogo();
                    }
                })
                .setCancelable(false)
                .show();
    }

    private void reiniciarJogo() {
        // TODO: 28/03/2017 fazer o jogo reiniciar
        startActivity(new Intent(this, TelaJogo.class));
        finish();
    }
}
