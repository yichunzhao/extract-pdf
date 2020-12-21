package com.ynz.pdf.extractpdf.statemachine.context;

import com.ynz.pdf.extractpdf.statemachine.state.State;

public interface Context<T extends State> {
    void setNextState(T state);

    T getCurrentState();
}
