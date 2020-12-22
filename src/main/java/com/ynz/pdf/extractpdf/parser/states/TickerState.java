package com.ynz.pdf.extractpdf.parser.states;

import com.ynz.pdf.extractpdf.statemachine.context.ARKLineTextContext;
import com.ynz.pdf.extractpdf.statemachine.state.ARKLineTextState;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TickerState implements ARKLineTextState {
    private static final String pattern = "[A-Z]{2,5}";
    private String word;

    @Override
    public void doAction(ARKLineTextContext context) {
        this.word = context.getWord();

        if (context.getCurrentState() instanceof TickerState) {
            if (word.matches(pattern)) {
                context.getModel().setTicker(word.trim());
                context.setNextState(new PriceState());
            } else {
                context.setNextState(new BrokenState());
            }
        }
    }
}
