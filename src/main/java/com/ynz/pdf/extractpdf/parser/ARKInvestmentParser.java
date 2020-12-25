package com.ynz.pdf.extractpdf.parser;

import com.ynz.pdf.extractpdf.model.ARKDataModel;
import com.ynz.pdf.extractpdf.parser.states.BrokenState;
import com.ynz.pdf.extractpdf.parser.states.DateState;
import com.ynz.pdf.extractpdf.statemachine.context.ARKLineTextContext;
import com.ynz.pdf.extractpdf.statemachine.state.ARKLineTextState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
@Getter
@Setter
public class ARKInvestmentParser implements TextParser<ARKDataModel>, ARKLineTextContext {
    private static final String linePattern = "^\\d{1,2}/\\d{1,2}/\\d{4}\\s+(Sell|Buy).+[$]\\d+.\\d{2}$";

    private ARKLineTextState currentState;

    private String line;

    private ARKDataModel model;

    @Override
    public List<ARKDataModel> parse(String text) {
        List<ARKDataModel> list = new ArrayList<>();

        String[] lines = text.split(System.lineSeparator());
        for (String line : lines) {
            if (!isValidLine(line)) continue;
            processLine(line);
            list.add(getModel());
        }

        return list;
    }

    public void processLine(String line) {
        this.setLine(line);
        model = new ARKDataModel();

        if (this.currentState == null || this.currentState instanceof BrokenState) setNextState(new DateState());

        while (!(this.currentState instanceof BrokenState)) {
            this.currentState.doAction(this);
        }
    }

    @Override
    public void setNextState(ARKLineTextState state) {
        this.currentState = state;
    }

    public boolean isValidLine(String target) {
        return target.matches(linePattern);
    }

}
