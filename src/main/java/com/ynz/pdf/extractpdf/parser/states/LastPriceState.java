package com.ynz.pdf.extractpdf.parser.states;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LastPriceState extends CurrencyPattern implements State {
    private String word;

    @Override
    public void doAction(Context context) {
        this.word = context.getWord();

        if (context.getCurrentState().equals(Columns.LAST_PRICE) && word.matches(curPattern)) {

            context.setNextState(Columns.DATE);
        }
    }
}
