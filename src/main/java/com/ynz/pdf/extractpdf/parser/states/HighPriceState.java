package com.ynz.pdf.extractpdf.parser.states;

import com.ynz.pdf.extractpdf.statemachine.context.ARKLineTextContext;
import com.ynz.pdf.extractpdf.statemachine.state.ARKLineTextState;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HighPriceState extends CurrencyPattern implements ARKLineTextState {
    private String word;

    @Override
    public void doAction(ARKLineTextContext context) {
        this.word = context.getWord();
        if (context.getCurrentState().equals(Columns.HIGH_PRICE) && word.matches(curPattern)) {
            context.getModel().setHighPrice(word);
            context.setNextState(Columns.CLOSING_PRICE);
        }
    }
}
