package com.ynz.pdf.extractpdf.parser.states;

public class DirectionState implements State {
    private String word;

    public DirectionState(String word) {
        this.word = word;
    }

    @Override
    public void doAction(Context context) {
        //direction
        if (context.getCurrentState().equals(Columns.DIRECTION) && (word.equals("Sell") || word.equals("Buy"))) {

            context.setNextState(Columns.TICKER);
        }

    }
}
