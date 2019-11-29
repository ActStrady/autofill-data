package com.actstrady.autofilldata.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class Word2PdfTest {

    @Test
    void convertWord2Pdf() {
        String wordFile = System.getProperty("user.dir") + "/src/main/resources/doc/测试文档.docx";
        String pdfFile =
                System.getProperty("user.dir") + "/src/main/resources/doc/测试文档" + System.currentTimeMillis() + ".pdf";
        // Word2Pdf.convertWord2Pdf(wordFile, pdfFile);
        Word2Pdf.convertWord2PdfWithSpire(wordFile, pdfFile);
    }

    @Test
    void docx2Html() throws IOException {
        String wordFile = System.getProperty("user.dir") + "/src/main/resources/doc/测试文档.docx";
        Word2Pdf.docx2Html(wordFile);
    }
}