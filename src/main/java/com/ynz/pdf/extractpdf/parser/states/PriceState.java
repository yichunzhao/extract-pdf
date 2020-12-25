package com.ynz.pdf.extractpdf.parser.states;

import com.ynz.pdf.extractpdf.statemachine.context.ARKLineTextContext;
import com.ynz.pdf.extractpdf.statemachine.state.ARKLineTextState;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
public class PriceState implements ARKLineTextState {
    protected static final String curPattern = "[$][\\d,]+.\\d{2}";
    private String line;

    @Override
    public void doAction(ARKLineTextContext context) {
        this.line = context.getLine();

        if (context.getCurrentState() instanceof PriceState) {
            Matcher matcher = Pattern.compile(curPattern).matcher(line);
            int count = 0;
            while (matcher.find()) {
                count++;
                if (count == 1) context.getModel().setPrice(line.substring(matcher.start(), matcher.end()));
                if (count == 2) context.getModel().setLowPrice(line.substring(matcher.start(), matcher.end()));
                if (count == 3) context.getModel().setHighPrice(line.substring(matcher.start(), matcher.end()));
                if (count == 4) context.getModel().setClosingPrice(line.substring(matcher.start(), matcher.end()));
                if (count == 5) context.getModel().setRecentMarketPrice(line.substring(matcher.start(), matcher.end()));
            }
            context.setNextState(new BrokenState());
        }
    }
}
