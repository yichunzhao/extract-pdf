package com.ynz.pdf.extractpdf.parser;

import com.ynz.pdf.extractpdf.model.ARKInvestmentDataModel;
import com.ynz.pdf.extractpdf.parser.states.Columns;
import com.ynz.pdf.extractpdf.parser.states.Context;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ARKInvestmentParser implements TextParser<ARKInvestmentDataModel>, Context {

    private Columns state = Columns.DATE;

    @Override
    public List<ARKInvestmentDataModel> parse(String text) {
        String[] lines = text.split(System.lineSeparator());
        for (String line : lines) {
            String[] words = line.split("\\s");

            for (String word : words) {
                //date
                if (word.matches("\\d{1,2}\\d{1,2}\\d{4}")) {

                }

                //ticker
                if (word.equals("Sell") | word.equals("Buy")) {

                }

                //price
                if (word.matches("[A-Z]{2,4}")) {

                }

                //low price
                if (word.matches("[1-9]+\\.[1-9]{2}")) {

                }

                //high price
                if (word.matches("[1-9]+\\.[1-9]{2}")) {

                }

                //closing price
                if (word.matches("[1-9]+\\.[1-9]{2}")) {

                }

                //last price
                if (word.matches("[1-9]+\\.[1-9]{2}")) {

                }


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
}
