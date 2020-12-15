package com.ynz.pdf.extractpdf.parser.states;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TickerState implements State {
    private String word;

    @Override
    public void doAction(Context context) {
        this.word = context.getWord();

        if (context.getCurrentState().equals(Columns.TICKER)) {

            if (word.matches("[A-Z]{2,5}")) {

                context.setNextState(Columns.PRICE);
            }
        }
    }
}
