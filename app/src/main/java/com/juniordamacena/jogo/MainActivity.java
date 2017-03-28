package com.juniordamacena.jogo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickMenu(View view) {
        switch (view.getId()) {
            case R.id.btnNovoJogoSingle:
                // Iniciar o jogo no modo 1 jogador
                iniciarJogoSingle();
                break;
            case R.id.btnNovoJogoMulti:
                // Iniciar o jogo no modo 2 jogadores
                iniciarJogoMulti();
                break;
            case R.id.btnRecordes:
                // Abrir tela com os recordes
                abrirTelaRecordes();
                break;
            case R.id.btnSair:
                sairDoJogo();
                break;
        }
    }

    /**
     * Método responsável pela abertura do jogo
     */
    private void iniciarJogoSingle() {
        Intent intent = new Intent(this, TelaJogo.class);
        intent.putExtra(ModosJogo.getNome(), ModosJogo.SINGLE);
        startActivity(intent);
    }

    /**
     * Método responsável pela abertura do jogo
     */
    private void iniciarJogoMulti() {
        Intent intent = new Intent(this, TelaJogo.class);
        intent.putExtra("modo", ModosJogo.MULTI);
        startActivity(intent);
    }

    /**
     * Método responsável por abrir a tela de recordes
     */
    private void abrirTelaRecordes() {
        startActivity(new Intent(this, TelaRecordes.class));
    }

    @Override
    public void onBackPressed() {
        sairDoJogo();
    }

    /**
     * Método responsável pela lógica de saída do jogo
     */
    private void sairDoJogo() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialogoSairJogoTitulo)
                .setPositiveButton(R.string.dialogoSairJogoBtnSairDoJogo, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton(R.string.dialogoSairJogoBtnFicarNoJogo, null)
                .show();
    }

}
