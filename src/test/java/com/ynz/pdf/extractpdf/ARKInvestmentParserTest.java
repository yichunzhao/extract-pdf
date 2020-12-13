package com.ynz.pdf.extractpdf;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class ARKInvestmentParserTest {
    @Autowired
    private PdfToText pdfToText;

    private String text;

    @BeforeEach
    void setUp() {
        log.info("fetching the pdf content in a text String");
        text = pdfToText.parse(new File("ARK_Trades.pdf"));
    }

    @Test
    void validateIfPdfToTextBeanIsCreated() {
        assertNotNull(pdfToText);
    }

    @Test
    void ifTextIsSplitIntoStringArray() {
        String[] lines = Pattern.compile("\\r\\n").split(text);
        //it must have >100 lines
        assertThat(lines.length, is(greaterThan(100)));
    }

    @Test
    void testConvertingPdfTextToStringArrayBySystemLineSeparator() {
        String[] lines = Pattern.compile(System.lineSeparator()).split(text);
        String[] lines1 = Pattern.compile("\\r\\n").split(text);
        assertThat(lines.length, is(greaterThan(1)));
        assertEquals(lines.length, lines1.length);
    }

    @Test
    void parse() {
    }

    @Test
    void split() {
    }
}