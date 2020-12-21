package com.ynz.pdf.extractpdf.parser.states;

import com.ynz.pdf.extractpdf.statemachine.context.ARKLineTextContext;
import com.ynz.pdf.extractpdf.statemachine.state.ARKLineTextState;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LowPriceState extends CurrencyPattern implements ARKLineTextState {
    private String word;

    @Override
    public void doAction(ARKLineTextContext context) {
        this.word = context.getWord();

        if (context.getCurrentState().equals(Columns.LOW_PRICE) && word.matches(curPattern)) {
            context.getModel().setLowPrice(word);
            context.setNextState(Columns.HIGH_PRICE);
        }
    }
}
