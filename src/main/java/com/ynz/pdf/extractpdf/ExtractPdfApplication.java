package com.ynz.pdf.extractpdf;

import com.ynz.pdf.extractpdf.converter.PdfToText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileWriter;

@SpringBootApplication
public class ExtractPdfApplication implements CommandLineRunner {

    @Autowired
    private PdfToText pdfConverter;

    public static void main(String[] args) {
        SpringApplication.run(ExtractPdfApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String text = pdfConverter.parse(new File("ARK_Trades.pdf"));
        //System.out.println(text);

        File file = new File("myFile.txt");
        FileWriter writer = new FileWriter(file);
        writer.write(text);

    }

}
