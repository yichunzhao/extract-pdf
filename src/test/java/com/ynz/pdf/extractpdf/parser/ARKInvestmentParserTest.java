package com.ynz.pdf.extractpdf.parser;

import com.ynz.pdf.extractpdf.model.ARKDataModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ARKInvestmentParserTest {

    private ARKInvestmentParser parser = new ARKInvestmentParser();

    @Test
    void parse() {
    }

    @Test
    void givenLineWithCompletedStates_ParserCanFillALLAttributes() {
        String line = "9/14/2020 Sell CBMG $18.39 $18.16 $18.49 $18.38 $18.38";
        parser.processLine(line);

        ARKDataModel model = parser.getModel();

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

    @Test
    void givenCompleteARKReportLine_DetermineIfValid() {
        String target = "9/14/2020 Sell CBMG $18.39 $18.16 $18.49 $18.38 $18.38";
        assertTrue(parser.isValidLine(target));
    }

    @Test
    void givenIncompleteARKReportLine_DetermineInvalid() {
        String target = "9/15/2020";
        assertFalse(parser.isValidLine(target));
    }

    @Test
    void givenNotARKReportLine_DetermineInvalid() {
        String target = "Page 5 of 86\n";
        assertFalse(parser.isValidLine(target));
    }

    @Test
    void givenReportLines_ParserDetermines() {
        String lines =
                "Recent Market Price\r\n" +
                        "Date Direction Ticker Price Low Price High Price Closing Price Last Price As of \r\n" +
                        "9/15/2020\r\n" +
                        "9/14/2020 Sell CBMG $18.39 $18.16 $18.49 $18.38 $18.38\r\n" +
                        "9/14/2020 Buy EXAS $77.79 $76.75 $79.14 $77.60 $77.60\r\n";
        List<ARKDataModel> models = parser.parse(lines);

        assertAll(
                () -> assertThat(models, hasSize(2)),
                () -> assertThat(models.get(0).getDate(), is("9/14/2020")),
                () -> assertThat(models.get(1).getDirection(), is("Buy"))
        );
    }

    @Test
    void givenSpecificReportLine_ParseDetermine() {
        String lines = "9/10/2020 Buy WDI GR $0.91 $0.89 $0.95 $0.91 $0.91\r\n" +
                "9/10/2020 Buy 6060 HK $45.17 $43.40 $45.60 $43.40 $43.40\r\n" +
                "9/10/2020 Buy 9923 HK $48.77 $47.50 $49.50 $48.30 $48.30\r\n";

        List<ARKDataModel> models = parser.parse(lines);

        assertAll(
                () -> assertThat(models, hasSize(3)),
                () -> assertThat(models.get(0).getDate(), is("9/10/2020")),
                () -> assertThat(models.get(1).getDirection(), is("Buy"))
        );


    }

}