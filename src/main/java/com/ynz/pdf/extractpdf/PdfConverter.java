package com.ynz.pdf.extractpdf;


import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class PdfConverter implements PdfToText {
    private final PDFTextStripper textStripper;

    @Override
    public String parse(File pdf) {
        PDDocument document = null;
        String text = null;

        try {
            document = PDDocument.load(pdf);

            if (document != null) {
                text = textStripper.getText(document);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }
}
