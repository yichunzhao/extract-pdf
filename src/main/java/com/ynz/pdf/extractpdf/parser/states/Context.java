package com.ynz.pdf.extractpdf.parser.states;

public interface Context {
    void setNextState(Columns state);

    Columns getCurrentState();

    String getWord();
    String setWord(String word);
}
