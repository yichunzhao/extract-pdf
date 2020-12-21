package com.ynz.pdf.extractpdf.parser.states;

import com.ynz.pdf.extractpdf.parser.ARKInvestmentParser;
import com.ynz.pdf.extractpdf.statemachine.state.ARKLineTextState;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;


class DateStateTest {
    private ARKInvestmentParser parser = new ARKInvestmentParser();

    @Test
    void givenWordThatContainsDate_ContextStateIsSetToDirection() {
        DateState dateState = new DateState();
        parser.setNextState(dateState);

        parser.setWord("9/14/2020");
        dateState.doAction(parser);
        assertThat(parser.getCurrentState(), is(instanceOf(DirectionState.class)));
    }

    @Test
    void givenWordThatContainsDirection_ContextStateIsSetToTicker() {
        DirectionState directionState = new DirectionState();
        parser.setNextState(directionState);

        parser.setWord("Sell");
        directionState.doAction(parser);
        assertThat(parser.getCurrentState(), is(instanceOf(TickerState.class)));
    }

    @Test
    void givenWordThatContainsTicker2Capitals_ContextStateIsSetToPrice() {
        TickerState tickerState = new TickerState();
        parser.setNextState(tickerState);

        parser.setWord("LC");
        tickerState.doAction(parser);
        assertThat(parser.getCurrentState(), is(instanceOf(PriceState.class)));
    }

    @Test
    void givenWordThatContainsTicker5Capitals_ContextStateIsSetToPrice() {
        TickerState tickerState = new TickerState();
        parser.setNextState(tickerState);

        parser.setWord("NTDOY");
        tickerState.doAction(parser);
        assertThat(parser.getCurrentState(), is(instanceOf(PriceState.class)));
    }

    @Test
    void givenWordThatContainsPrice_ContextStateIsSetToLowPrice() {
        PriceState priceState = new PriceState();
        parser.setNextState(priceState);

        parser.setWord("$39.00");
        priceState.doAction(parser);
        assertThat(parser.getCurrentState(), is(instanceOf(LowPriceState.class)));
    }

    @Test
    void givenWordThatContainsHighPrice_ContextStateIsSetToClosingPrice() {
        ARKLineTextState highPriceState = new HighPriceState();
        parser.setNextState(highPriceState);

        parser.setWord("$39.00");
        highPriceState.doAction(parser);
        assertThat(parser.getCurrentState(), is(instanceOf(ClosingPriceState.class)));
    }

    @Test
    void givenWordThatContainsClosingPrice_ContextStateIsSetToLastPrice() {
        ARKLineTextState closingPriceState = new ClosingPriceState();
        parser.setNextState(closingPriceState);

        parser.setWord("$39.00");
        closingPriceState.doAction(parser);
        assertThat(parser.getCurrentState(), is(instanceOf(RecentMarketPriceState.class)));
    }

    @Test
    void givenWordThatContainsLastPrice_ContextStateIsSetToDateState() {
        ARKLineTextState lastPriceState = new RecentMarketPriceState();
        parser.setNextState(lastPriceState);

        parser.setWord("$39.00");
        lastPriceState.doAction(parser);
        assertThat(parser.getCurrentState(), is(instanceOf(DateState.class)));
    }

}