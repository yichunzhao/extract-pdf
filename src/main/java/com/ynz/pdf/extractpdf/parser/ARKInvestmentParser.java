package com.ynz.pdf.extractpdf.parser;

import com.ynz.pdf.extractpdf.model.ARKDataModel;
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

    private ARKLineTextState currentState;

    private String word;

    private ARKDataModel model = new ARKDataModel();

    @Override
    public List<ARKDataModel> parse(String text) {
        List<ARKDataModel> list = new ArrayList<>();

        String[] lines = text.split(System.lineSeparator());
        for (String line : lines) {
            processLine(line);
            if (validModel(getModel())) list.add(getModel());
        }

        return list;
    }

    private boolean validModel(ARKDataModel model) {
        return model.getDate() != null && model.getDirection() != null && model.getTicker() != null && model.getPrice() != null
                && model.getLowPrice() != null && model.getHighPrice() != null && model.getClosingPrice() != null
                && model.getRecentMarketPrice() != null;

    }

    public void processLine(String line) {
        String[] words = line.split("\\s");

        for (String word : words) {
            setWord(word);
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
}
