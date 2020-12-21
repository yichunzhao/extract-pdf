package com.ynz.pdf.extractpdf.parser.states;

import com.ynz.pdf.extractpdf.statemachine.context.ARKLineTextContext;
import com.ynz.pdf.extractpdf.statemachine.state.ARKLineTextState;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PriceState extends CurrencyPattern implements ARKLineTextState {
    private String word;

    @Override
    public void doAction(ARKLineTextContext context) {
        this.word = context.getWord();

        if (context.getCurrentState().equals(Columns.PRICE) && word.matches(curPattern)) {
            context.getModel().setPrice(word);
            context.setNextState(Columns.LOW_PRICE);
        }
    }
}
