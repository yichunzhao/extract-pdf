package com.ynz.pdf.extractpdf.parser.states;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TickerState implements State {
    private String word;

    @Override
    public void doAction(Context context) {

        if (context.getCurrentState().equals(Columns.TICKER)) {

            if (word.matches("[A-Z]{2,5}")) {

                context.setNextState(Columns.PRICE);
            }
        }

    }
}
