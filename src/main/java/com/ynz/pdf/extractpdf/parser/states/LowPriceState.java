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

        if (context.getCurrentState() instanceof LowPriceState && word.matches(curPattern)) {
            context.getModel().setLowPrice(word);
            context.setNextState(new HighPriceState());
        } else {
            context.setNextState(new BrokenState());
        }
    }
}
