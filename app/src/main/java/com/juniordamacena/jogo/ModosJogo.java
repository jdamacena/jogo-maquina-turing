package com.juniordamacena.jogo;
/*Created by juniordamacena on 27/03/2017.*/

/**
 * Essa classe tem o objetivo de centralizar todos os tipos de modo de jogo presentes no sistema
 */
enum ModosJogo {
    MULTI, SINGLE;

    private static final String nome = "modo";

    public static String getNome() {
        return nome;
    }
}
