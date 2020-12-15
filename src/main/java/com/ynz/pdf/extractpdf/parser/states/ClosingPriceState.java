package com.ynz.pdf.extractpdf.parser.states;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ClosingPriceState extends CurrencyPattern implements State {
    private String word;

    @Override
    public void doAction(Context context) {
        this.word = context.getWord();

        if (context.getCurrentState().equals(Columns.CLOSING_PRICE) && word.matches(curPattern)) {

            context.setNextState(Columns.LAST_PRICE);
        }

    }
}
