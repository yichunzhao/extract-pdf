package com.ynz.pdf.extractpdf.parser.states;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DateState implements State {
    private static final String datePattern = "\\d{1,2}/\\d{1,2}/\\d{4}";
    private String word;

    @Override
    public void doAction(Context context) {
        this.word = context.getWord();

        //date
        if (context.getCurrentState().equals(Columns.DATE) && word.matches(datePattern)) {
            context.getModel().setDate(word);
            context.setNextState(Columns.DIRECTION);
        }

    }
}
