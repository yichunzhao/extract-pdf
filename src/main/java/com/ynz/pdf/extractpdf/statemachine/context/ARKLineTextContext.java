package com.ynz.pdf.extractpdf.statemachine.context;

import com.ynz.pdf.extractpdf.model.ARKDataModel;

public interface ARKLineTextContext extends Context {
    String getWord();

    String setWord(String word);

    ARKDataModel getModel();
}
