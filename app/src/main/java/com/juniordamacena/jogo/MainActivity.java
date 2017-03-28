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
            case R.id.btnNovoJogo:
                // Iniciar o jogo no modo 1 jogador
                iniciarJogo();
                break;
            case R.id.btnSair:
                sairDoJogo();
                break;
        }
    }

    /**
     * Método responsável pela abertura do jogo
     */
    private void iniciarJogo() {
        Intent intent = new Intent(this, TelaJogo.class);
        startActivity(intent);
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
