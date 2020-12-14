package com.ynz.pdf.extractpdf.parser.states;

public class DateState implements State {
    private static final String pattern = "\\d{1,2}/\\d{1,2}/\\d{4}";
    private final String word;

    public DateState(String word) {
        this.word = word;
    }

    @Override
    public void doAction(Context context) {
        //date
        if (context.getCurrentState().equals(Columns.DATE) && word.matches(pattern)) {

            context.setNextState(Columns.DIRECTION);
        }

    }
}
