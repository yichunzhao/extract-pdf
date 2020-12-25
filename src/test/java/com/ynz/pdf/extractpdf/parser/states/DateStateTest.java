package com.ynz.pdf.extractpdf.parser.states;

import com.ynz.pdf.extractpdf.model.ARKDataModel;
import com.ynz.pdf.extractpdf.parser.ARKInvestmentParser;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;


class DateStateTest {
    private ARKInvestmentParser parser = new ARKInvestmentParser();

    {
        parser.setModel(new ARKDataModel());
    }

    @Test
    void givenWordThatContainsDate_ContextStateIsSetToDirection() {
        DateState dateState = new DateState();
        parser.setNextState(dateState);

        parser.setLine("8/26/2020 Sell 3690 HK $258.46 $251.60 $263.40 $258.80 $258.80");
        dateState.doAction(parser);
        assertThat(parser.getCurrentState(), is(instanceOf(DirectionState.class)));
    }

    @Test
    void givenWordThatContainsDirection_ContextStateIsSetToTicker() {
        DirectionState directionState = new DirectionState();
        parser.setNextState(directionState);

        parser.setLine("8/28/2020 Sell TSLA $2,279.06 $437.30 $463.70 $442.68 $442.68");
        directionState.doAction(parser);
        assertThat(parser.getCurrentState(), is(instanceOf(TickerState.class)));
    }

    @Test
    void givenWordThatContainsTicker2Capitals_ContextStateIsSetToPrice() {
        TickerState tickerState = new TickerState();
        parser.setNextState(tickerState);

        parser.setLine("8/28/2020 Sell TSLA $2,279.06 $437.30 $463.70 $442.68 $442.68");
        tickerState.doAction(parser);
        assertThat(parser.getCurrentState(), is(instanceOf(PriceState.class)));
    }

    @Test
    void givenWordThatContainsTicker5Capitals_ContextStateIsSetToPrice() {
        TickerState tickerState = new TickerState();
        parser.setNextState(tickerState);

        parser.setLine("8/26/2020 Sell 3690 HK $258.46 $251.60 $263.40 $258.80 $258.80");
        tickerState.doAction(parser);
        assertThat(parser.getCurrentState(), is(instanceOf(PriceState.class)));
    }

    @Test
    void givenWordThatContainsPrice_ContextStateIsSetToBrokenState() {
        PriceState priceState = new PriceState();
        parser.setNextState(priceState);

        parser.setLine("8/26/2020 Sell 3690 HK $258.46 $251.60 $263.40 $258.80 $258.80");
        priceState.doAction(parser);
        assertThat(parser.getCurrentState(), is(instanceOf(BrokenState.class)));

        assertAll(
                () -> assertThat(parser.getCurrentState(), is(instanceOf(BrokenState.class))),
                () -> assertThat(parser.getModel().getPrice(), is("$258.46")),
                () -> assertThat(parser.getModel().getLowPrice(), is("$251.60")),
                () -> assertThat(parser.getModel().getHighPrice(), is("$263.40")),
                () -> assertThat(parser.getModel().getClosingPrice(), is("$258.80")),
                () -> assertThat(parser.getModel().getRecentMarketPrice(), is("$258.80"))
        );
    }

    @Test
    void givenWordThatContainsPriceHavingComma_ContextStateIsSetToBrokenState() {
        PriceState priceState = new PriceState();
        parser.setNextState(priceState);

        parser.setLine("8/20/2020 Sell TSLA $1,990.24 $371.41 $404.40 $400.37 $400.37");
        priceState.doAction(parser);
        assertThat(parser.getCurrentState(), is(instanceOf(BrokenState.class)));

        assertAll(
                () -> assertThat(parser.getCurrentState(), is(instanceOf(BrokenState.class))),
                () -> assertThat(parser.getModel().getPrice(), is("$1,990.24")),
                () -> assertThat(parser.getModel().getLowPrice(), is("$371.41")),
                () -> assertThat(parser.getModel().getHighPrice(), is("$404.40")),
                () -> assertThat(parser.getModel().getClosingPrice(), is("$400.37")),
                () -> assertThat(parser.getModel().getRecentMarketPrice(), is("$400.37"))
        );
    }


}