package com.ynz.pdf.extractpdf.parser.states;

import com.ynz.pdf.extractpdf.parser.ARKInvestmentParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DateStateTest {
    private ARKInvestmentParser parser = new ARKInvestmentParser();


    @Test
    void givenWordThatContainsDate_ContextStateIsSetToDirection() {
        DateState dateState = new DateState("9/14/2020");
        assertEquals(parser.getCurrentState(), Columns.DATE);

        dateState.doAction(parser);
        assertEquals(Columns.DIRECTION, parser.getCurrentState());
    }

    @Test
    void givenWordThatContainsDirection_ContextStateIsSetToTicker() {
        DirectionState directionState = new DirectionState("Sell");
        parser.setNextState(Columns.DIRECTION);
        assertEquals(parser.getCurrentState(), Columns.DIRECTION);

        directionState.doAction(parser);
        assertEquals(Columns.TICKER, parser.getCurrentState());
    }

    @Test
    void givenWordThatContainsTicker2Capitals_ContextStateIsSetToPrice() {
        TickerState tickerState = new TickerState("LC");
        parser.setNextState(Columns.TICKER);
        assertEquals(parser.getCurrentState(), Columns.TICKER);

        tickerState.doAction(parser);
        assertEquals(Columns.PRICE, parser.getCurrentState());
    }

    @Test
    void givenWordThatContainsTicker5Capitals_ContextStateIsSetToPrice() {
        TickerState tickerState = new TickerState("NTDOY");
        parser.setNextState(Columns.TICKER);
        assertEquals(parser.getCurrentState(), Columns.TICKER);

        tickerState.doAction(parser);
        assertEquals(Columns.PRICE, parser.getCurrentState());
    }

    @Test
    void givenWordThatContainsPrice_ContextStateIsSetToLowPrice() {
        PriceState priceState = new PriceState("$39.00");
        parser.setNextState(Columns.PRICE);
        assertEquals(parser.getCurrentState(), Columns.PRICE);

        priceState.doAction(parser);
        assertEquals(Columns.LOW_PRICE, parser.getCurrentState());
    }

    @Test
    void givenWordThatContainsHighPrice_ContextStateIsSetToClosingPrice() {
        State highPriceState = new HighPrice("$39.00");
        parser.setNextState(Columns.HIGH_PRICE);
        assertEquals(parser.getCurrentState(), Columns.HIGH_PRICE);

        highPriceState.doAction(parser);
        assertEquals(Columns.CLOSING_PRICE, parser.getCurrentState());
    }

    @Test
    void givenWordThatContainsClosingPrice_ContextStateIsSetToLastPrice() {
        State closingPriceState = new ClosingPriceState("$39.00");
        parser.setNextState(Columns.CLOSING_PRICE);
        assertEquals(parser.getCurrentState(), Columns.CLOSING_PRICE);

        closingPriceState.doAction(parser);
        assertEquals(Columns.LAST_PRICE, parser.getCurrentState());
    }

    @Test
    void givenWordThatContainsLastPrice_ContextStateIsSetToDateState() {
        State lastPriceState = new LastPriceState("$39.00");
        parser.setNextState(Columns.LAST_PRICE);
        assertEquals(parser.getCurrentState(), Columns.LAST_PRICE);

        lastPriceState.doAction(parser);
        assertEquals(Columns.DATE, parser.getCurrentState());
    }


}