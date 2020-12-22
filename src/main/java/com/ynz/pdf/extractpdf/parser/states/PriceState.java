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

        if (context.getCurrentState() instanceof PriceState && word.matches(curPattern)) {
            context.getModel().setPrice(word);
            context.setNextState(new LowPriceState());
        } else {
            context.setNextState(new BrokenState());
        }
    }
}
