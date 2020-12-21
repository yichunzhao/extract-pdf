package com.ynz.pdf.extractpdf.parser.states;

import com.ynz.pdf.extractpdf.statemachine.context.ARKLineTextContext;
import com.ynz.pdf.extractpdf.statemachine.context.Context;
import com.ynz.pdf.extractpdf.statemachine.state.ARKLineTextState;
import com.ynz.pdf.extractpdf.statemachine.state.State;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ClosingPriceState extends CurrencyPattern implements ARKLineTextState {
    private String word;

    @Override
    public void doAction(ARKLineTextContext context) {
        this.word = context.getWord();

        if (context.getCurrentState().equals(Columns.CLOSING_PRICE) && word.matches(curPattern)) {

            context.getModel().setClosingPrice(word.trim());
            context.setNextState(Columns.LAST_PRICE);
        }

    }

}
