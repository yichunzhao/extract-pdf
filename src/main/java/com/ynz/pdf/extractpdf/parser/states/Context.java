package com.ynz.pdf.extractpdf.parser.states;

import com.ynz.pdf.extractpdf.model.ARKInvestmentDataModel;

public interface Context {
    void setNextState(Columns state);

    Columns getCurrentState();

    String getWord();
    String setWord(String word);

    ARKInvestmentDataModel getModel();
}
