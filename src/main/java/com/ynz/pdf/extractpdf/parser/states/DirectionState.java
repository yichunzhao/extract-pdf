package com.ynz.pdf.extractpdf.parser.states;

import com.ynz.pdf.extractpdf.statemachine.context.ARKLineTextContext;
import com.ynz.pdf.extractpdf.statemachine.state.ARKLineTextState;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
public class DirectionState implements ARKLineTextState {
    private static final String dPattern = "Sell|Buy";
    private String line;

    @Override
    public void doAction(ARKLineTextContext context) {
        this.line = context.getLine();

        //direction
        if (context.getCurrentState() instanceof DirectionState) {

            Matcher matcher = Pattern.compile(dPattern).matcher(line);
            if (matcher.find()) {
                context.getModel().setDirection(line.substring(matcher.start(), matcher.end()));
            }
            context.setNextState(new TickerState());
        }
    }
}
