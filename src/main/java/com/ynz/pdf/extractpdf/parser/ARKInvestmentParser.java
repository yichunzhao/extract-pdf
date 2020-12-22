package com.ynz.pdf.extractpdf.parser;

import com.ynz.pdf.extractpdf.model.ARKDataModel;
import com.ynz.pdf.extractpdf.parser.states.BrokenState;
import com.ynz.pdf.extractpdf.parser.states.DateState;
import com.ynz.pdf.extractpdf.statemachine.context.ARKLineTextContext;
import com.ynz.pdf.extractpdf.statemachine.state.ARKLineTextState;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@NoArgsConstructor
public class ARKInvestmentParser implements TextParser<ARKDataModel>, ARKLineTextContext {
    private static final String linePattern = "^\\d{1,2}/\\d{1,2}/\\d{4}.+[$]{1}\\d+.\\d{2}$";

    private ARKLineTextState currentState;

    private String word;

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
        String[] words = line.split("\\s");
        model = new ARKDataModel();

        for (String word : words) {
            if (this.currentState instanceof BrokenState) break;
            this.setWord(word);
            if (this.currentState == null) setNextState(new DateState());
            this.currentState.doAction(this);
        }
    }

    @Override
    public void setNextState(ARKLineTextState state) {
        this.currentState = state;
    }

    @Override
    public ARKLineTextState getCurrentState() {
        return this.currentState;
    }

    @Override
    public String getWord() {
        return this.word;
    }

    public String setWord(String word) {
        return this.word = word;
    }

    @Override
    public ARKDataModel getModel() {
        return this.model;
    }

    public boolean isValidLine(String target) {
        return target.matches(linePattern);
    }

}
