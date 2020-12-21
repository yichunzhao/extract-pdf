package com.ynz.pdf.extractpdf.parser;

import com.ynz.pdf.extractpdf.model.ARKDataModel;
import com.ynz.pdf.extractpdf.parser.states.ClosingPriceState;
import com.ynz.pdf.extractpdf.parser.states.Columns;
import com.ynz.pdf.extractpdf.parser.states.Context;
import com.ynz.pdf.extractpdf.parser.states.DateState;
import com.ynz.pdf.extractpdf.parser.states.DirectionState;
import com.ynz.pdf.extractpdf.parser.states.HighPriceState;
import com.ynz.pdf.extractpdf.parser.states.LastPriceState;
import com.ynz.pdf.extractpdf.parser.states.LowPriceState;
import com.ynz.pdf.extractpdf.parser.states.PriceState;
import com.ynz.pdf.extractpdf.parser.states.State;
import com.ynz.pdf.extractpdf.parser.states.TickerState;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class ARKInvestmentParser implements TextParser<ARKDataModel>, Context {

    private Columns state = Columns.DATE;

    private String word = null;

    private ARKDataModel model = new ARKDataModel();

    public ARKInvestmentParser() {

    }

    DateState dateState = new DateState();
    DirectionState directionState = new DirectionState();
    TickerState tickerState = new TickerState();
    PriceState priceState = new PriceState();
    LowPriceState lowPriceState = new LowPriceState();
    HighPriceState highPriceState = new HighPriceState();
    ClosingPriceState closingPriceState = new ClosingPriceState();
    LastPriceState lastPriceState = new LastPriceState();

    List<State> stateMachine = new LinkedList<>();


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

            switch (this.getCurrentState()) {
                case DATE:
                    dateState.doAction(this);
                    break;
                case DIRECTION:
                    directionState.doAction(this);
                    break;
                case TICKER:
                    tickerState.doAction(this);
                    break;
                case PRICE:
                    priceState.doAction(this);
                    break;
                case LOW_PRICE:
                    lowPriceState.doAction(this);
                    break;
                case HIGH_PRICE:
                    highPriceState.doAction(this);
                    break;
                case CLOSING_PRICE:
                    closingPriceState.doAction(this);
                    break;
                case LAST_PRICE:
                    lastPriceState.doAction(this);
                    break;
                default:
                    System.out.println("wrong state");
                    break;
            }

        }

    }

    @Override
    public void setNextState(Columns state) {
        this.state = state;
    }

    @Override
    public Columns getCurrentState() {
        return this.state;
    }

    @Override
    public String getWord() {
        return this.word;
    }

    @Override
    public String setWord(String word) {
        return this.word = word;
    }

    @Override
    public ARKDataModel getModel() {
        return this.model;
    }
}
