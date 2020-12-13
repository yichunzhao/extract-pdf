package com.ynz.pdf.extractpdf;

import java.util.List;

public interface TextParser<T extends DataModel> {

    /**
     * the data text won't be regular and fit the data model.
     * according to field formatting feature and parse the text into data model.
     *
     * @Param String input text String
     * @Return List a list of DataModel
     */
    List<T> parse(String text);
}
