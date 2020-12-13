package com.ynz.pdf.extractpdf.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class ARKInvestmentDataModel implements DataModel {
    private LocalDate date;
    private Direction direction;
    private String ticker;
    private BigDecimal lowPrice;
    private BigDecimal highPrice;
    private BigDecimal closingPrice;
    private BigDecimal recentMarketPrice;
}
