package com.ynz.pdf.extractpdf.parser.states;

import com.ynz.pdf.extractpdf.statemachine.context.ARKLineTextContext;
import com.ynz.pdf.extractpdf.statemachine.state.ARKLineTextState;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
public class TickerState implements ARKLineTextState {
    private static final String pattern = "((\\s[0-9]{4}\\s|\\s[A-Z]+\\s)([A-Z]{2})?)";
    private String line;

    @Override
    public void doAction(ARKLineTextContext context) {
        this.line = context.getLine();

        if (context.getCurrentState() instanceof TickerState) {
            Matcher matcher = Pattern.compile(pattern).matcher(line);
            if (matcher.find()) {
                context.getModel().setTicker(line.trim());
            }
            context.setNextState(new PriceState());
        }
    }
}

