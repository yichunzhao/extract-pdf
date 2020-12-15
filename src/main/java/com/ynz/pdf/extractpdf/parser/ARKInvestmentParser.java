package com.ynz.pdf.extractpdf.parser;

import com.ynz.pdf.extractpdf.model.ARKInvestmentDataModel;
import com.ynz.pdf.extractpdf.parser.states.ClosingPriceState;
import com.ynz.pdf.extractpdf.parser.states.Columns;
import com.ynz.pdf.extractpdf.parser.states.Context;
import com.ynz.pdf.extractpdf.parser.states.DateState;
import com.ynz.pdf.extractpdf.parser.states.HighPriceState;
import com.ynz.pdf.extractpdf.parser.states.LastPriceState;
import com.ynz.pdf.extractpdf.parser.states.LowPriceState;
import com.ynz.pdf.extractpdf.parser.states.PriceState;
import com.ynz.pdf.extractpdf.parser.states.TickerState;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ARKInvestmentParser implements TextParser<ARKInvestmentDataModel>, Context {

    private Columns state = Columns.DATE;

    private String word = null;

    DateState dateState = new DateState();
    TickerState tickerState = new TickerState();
    PriceState priceState = new PriceState();
    LowPriceState lowPriceState = new LowPriceState();
    HighPriceState highPriceState = new HighPriceState();
    ClosingPriceState closingPriceState = new ClosingPriceState();
    LastPriceState lastPriceState = new LastPriceState();


    @Override
    public List<ARKInvestmentDataModel> parse(String text) {
        String[] lines = text.split(System.lineSeparator());
        for (String line : lines) {
            String[] words = line.split("\\s");

            for (String word : words) {
                setWord(word);
                //date
                dateState.doAction(this);

                //ticker
                tickerState.doAction(this);

                //price
                priceState.doAction(this);

                //low price
                lowPriceState.doAction(this);

                //high price
                highPriceState.doAction(this);

                //closing price
                closingPriceState.doAction(this);

                //last price
                lastPriceState.doAction(this);

            }
        }

        return null;
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
}
