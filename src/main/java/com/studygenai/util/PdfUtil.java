package com.studygenai.util;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PdfUtil {

    public static String extractText(File file) throws IOException {

        PDDocument document = null;

        try {

            System.out.println("Opening PDF: " + file.getAbsolutePath());

            document = Loader.loadPDF(file);

            PDFTextStripper stripper = new PDFTextStripper();

            String text = stripper.getText(document);

            System.out.println("PDF loaded successfully.");
            System.out.println("Extracted text length: " + text.length());

            return text;

        } catch (Exception e) {

            System.out.println("Error while extracting PDF text:");
            e.printStackTrace();

            throw e;

        } finally {

            if (document != null) {
                document.close();
            }

        }

    }

}