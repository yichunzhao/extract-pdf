package com.ynz.pdf.extractpdf.parser.states;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LastPriceState implements State {
    private final String word;

    @Override
    public void doAction(Context context) {
        if (context.getCurrentState().equals(Columns.LAST_PRICE) && word.matches("[$]\\d+.\\d{2}")) {

            context.setNextState(Columns.DATE);
        }
    }
}
