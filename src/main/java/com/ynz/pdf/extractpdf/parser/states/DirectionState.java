package com.ynz.pdf.extractpdf.parser.states;

import com.ynz.pdf.extractpdf.statemachine.context.ARKLineTextContext;
import com.ynz.pdf.extractpdf.statemachine.state.ARKLineTextState;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DirectionState implements ARKLineTextState {
    private String word;

    @Override
    public void doAction(ARKLineTextContext context) {
        this.word = context.getWord();

        //direction
        if (context.getCurrentState() instanceof DirectionState && (word.equals("Sell") || word.equals("Buy"))) {
            context.getModel().setDirection(word.trim());
            context.setNextState(new TickerState());
        } else {
            context.setNextState(new BrokenState());
        }
    }
}
