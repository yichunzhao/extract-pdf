package com.ynz.pdf.extractpdf.parser.states;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClosingPriceState implements State {
    private final String word;

    @Override
    public void doAction(Context context) {
        if (context.getCurrentState().equals(Columns.CLOSING_PRICE) && word.matches("[$]\\d+.\\d{2}")) {

            context.setNextState(Columns.LAST_PRICE);
        }

    }
}
