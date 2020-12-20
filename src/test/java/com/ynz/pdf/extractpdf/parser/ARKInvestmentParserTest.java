package com.ynz.pdf.extractpdf.parser;

import com.ynz.pdf.extractpdf.model.ARKInvestmentDataModel;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;

class ARKInvestmentParserTest {

    private ARKInvestmentParser parser = new ARKInvestmentParser();

    @Test
    void parse() {
    }

    @Test
    void processLine() {
        String line = "9/14/2020 Sell CBMG $18.39 $18.16 $18.49 $18.38 $18.38";
        parser.processLine(line);

        ARKInvestmentDataModel model = parser.getModel();

        assertAll(
                () -> assertThat(model.getDate(), is("9/14/2020")),
                () -> assertThat(model.getDirection(), is("Sell")),
                () -> assertThat(model.getTicker(), is("CBMG")),
                () -> assertThat(model.getPrice(), is("$18.39")),
                () -> assertThat(model.getLowPrice(), is("$18.16")),
                () -> assertThat(model.getHighPrice(), is("$18.49")),
                () -> assertThat(model.getClosingPrice(), is("$18.38")),
                () -> assertThat(model.getRecentMarketPrice(), is("$18.38"))
        );

    }
}