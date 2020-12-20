package com.ynz.pdf.extractpdf.parser.states;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LowPriceState extends CurrencyPattern implements State {
    private String word;

    @Override
    public void doAction(Context context) {
        this.word = context.getWord();

        if (context.getCurrentState().equals(Columns.LOW_PRICE) && word.matches(curPattern)) {

            context.getModel().setLowPrice(word);
            context.setNextState(Columns.HIGH_PRICE);
        }
    }
}
