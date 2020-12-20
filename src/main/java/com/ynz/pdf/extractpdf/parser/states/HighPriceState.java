package com.ynz.pdf.extractpdf.parser.states;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HighPriceState extends CurrencyPattern implements State {
    private String word;

    @Override
    public void doAction(Context context) {
        this.word = context.getWord();
        if (context.getCurrentState().equals(Columns.HIGH_PRICE) && word.matches(curPattern)) {
            context.getModel().setHighPrice(word);
            context.setNextState(Columns.CLOSING_PRICE);
        }
    }
}
