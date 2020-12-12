package com.ynz.pdf.extractpdf;

import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class AppConfig {

    @Bean
    public PDFTextStripper getPdfStripper() {
        PDFTextStripper pdfTextStripper = null;
        try {
            pdfTextStripper = new PDFTextStripper();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pdfTextStripper;
    }
}
