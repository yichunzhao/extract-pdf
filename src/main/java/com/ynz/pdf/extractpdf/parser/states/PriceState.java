package com.ynz.pdf.extractpdf.parser.states;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PriceState implements State {
    private String word;

    @Override
    public void doAction(Context context) {
        if (context.getCurrentState().equals(Columns.PRICE) && word.matches("[$]\\d+.\\d{2}")) {

            context.setNextState(Columns.LOW_PRICE);
        }

    }
}
