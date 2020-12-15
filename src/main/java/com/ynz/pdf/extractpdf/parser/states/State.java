package com.ynz.pdf.extractpdf.parser.states;

public interface State {
    void doAction(Context context);

    //void doAction(Context context, String word);
}
