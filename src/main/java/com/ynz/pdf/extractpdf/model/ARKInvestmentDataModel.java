package com.ynz.pdf.extractpdf.model;

import lombok.Data;

@Data
public class ARKInvestmentDataModel implements DataModel {
    private String date;
    private String direction;
    private String ticker;
    private String price;
    private String lowPrice;
    private String highPrice;
    private String closingPrice;
    private String recentMarketPrice;
}
