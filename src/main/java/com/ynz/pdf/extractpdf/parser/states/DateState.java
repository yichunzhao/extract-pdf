package com.ynz.pdf.extractpdf.parser.states;

import com.ynz.pdf.extractpdf.statemachine.context.ARKLineTextContext;
import com.ynz.pdf.extractpdf.statemachine.state.ARKLineTextState;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
public class DateState implements ARKLineTextState {
    private static final String datePattern = "\\d{1,2}/\\d{1,2}/\\d{4}";
    private String line;

    @Override
    public void doAction(ARKLineTextContext context) {
        this.line = context.getLine();

        //date
        if ((context.getCurrentState() instanceof DateState)) {
            Matcher matcher = Pattern.compile(datePattern).matcher(line);

            while (matcher.find()) {
                context.getModel().setDate(line.substring(matcher.start(), matcher.end()).trim());
            }
            context.setNextState(new DirectionState());
        }
    }
}
