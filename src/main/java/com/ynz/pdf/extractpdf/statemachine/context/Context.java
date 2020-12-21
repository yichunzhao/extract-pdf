package com.ynz.pdf.extractpdf.statemachine.context;

import com.ynz.pdf.extractpdf.parser.states.Columns;

public interface Context {
    void setNextState(Columns state);

    Columns getCurrentState();
}
