package com.ynz.pdf.extractpdf.parser.states;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DirectionState implements State {
    private String word;

    @Override
    public void doAction(Context context) {
        this.word = context.getWord();

        //direction
        if (context.getCurrentState().equals(Columns.DIRECTION) && (word.equals("Sell") || word.equals("Buy"))) {

            context.getModel().setDirection(word.trim());

            context.setNextState(Columns.TICKER);
        }
    }
}
