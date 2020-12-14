package com.ynz.pdf.extractpdf.parser.states;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LowPrice implements State {
    private final String word;

    @Override
    public void doAction(Context context) {

        if (context.getCurrentState().equals(Columns.LOW_PRICE) && word.matches("[$]\\d+.\\d{2}")) {

            context.setNextState(Columns.HIGH_PRICE);
        }
    }
}
