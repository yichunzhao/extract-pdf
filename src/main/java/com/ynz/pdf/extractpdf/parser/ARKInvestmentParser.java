package com.ynz.pdf.extractpdf.parser;

import com.ynz.pdf.extractpdf.model.ARKInvestmentDataModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Component
public class ARKInvestmentParser implements TextParser<ARKInvestmentDataModel> {

    @Override
    public List<ARKInvestmentDataModel> parse(String text) {
        return null;
    }

    public Stream<String> split(String text) {
        return Pattern.compile("/n").splitAsStream(text);
    }
}
