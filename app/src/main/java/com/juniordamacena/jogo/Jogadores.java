package com.juniordamacena.jogo;
/*Created by juniordamacena on 28/03/2017.*/

enum Jogadores {
    JOGADOR01(1), JOGADOR02(3);

    private int indiceDoDrawble;

    Jogadores(int indiceDoDrawble) {
        this.indiceDoDrawble = indiceDoDrawble;
    }

    public int getIndiceDoDrawble() {
        return indiceDoDrawble;
    }

    public void setIndiceDoDrawble(int indiceDoDrawble) {
        this.indiceDoDrawble = indiceDoDrawble;
    }


    @Override
    public String toString() {
        switch (this) {
            case JOGADOR01:
                return "Jogador 01";
            case JOGADOR02:
                return "Jogador 02";
            default:
                return "Vamos dizer que foi empate...";
        }
    }
}
