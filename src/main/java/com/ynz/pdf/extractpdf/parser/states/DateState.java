package com.ynz.pdf.extractpdf.parser.states;

import com.ynz.pdf.extractpdf.statemachine.context.ARKLineTextContext;
import com.ynz.pdf.extractpdf.statemachine.state.ARKLineTextState;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DateState implements ARKLineTextState {
    private static final String datePattern = "\\d{1,2}/\\d{1,2}/\\d{4}";
    private String word;

    @Override
    public void doAction(ARKLineTextContext context) {
        this.word = context.getWord();

        //date
        if ((context.getCurrentState() instanceof DateState) && word.matches(datePattern)) {
            context.getModel().setDate(word);
            context.setNextState(new DirectionState());
        } else {
            context.setNextState(new BrokenState());
        }
    }
}
