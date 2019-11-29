package com.actstrady.autofilldata.utils;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.docx4j.Docx4J;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import com.spire.doc.*;

import java.io.*;

/**
 * @author ActStrady
 * @date 2019/11/29
 */
public class Word2Pdf {

    /**
     * 使用docx4j-export-fo来处理
     *
     * @param wordFile word文件
     * @param pdfFile  pdf文件
     */
    public static void convertWord2Pdf(String wordFile, String pdfFile) {

        try (OutputStream osPdf = new FileOutputStream(pdfFile)) {
            File isWord = new File(wordFile);
            WordprocessingMLPackage mlPackage = WordprocessingMLPackage.load(isWord);
            // Mapper fontMapper = new IdentityPlusMapper();
            // fontMapper.put("隶书", PhysicalFonts.get("LiSu"));
            // fontMapper.put("宋体", PhysicalFonts.get("SimSun"));
            // fontMapper.put("微软雅黑", PhysicalFonts.get("Microsoft Yahei"));
            // fontMapper.put("黑体", PhysicalFonts.get("SimHei"));
            // fontMapper.put("楷体", PhysicalFonts.get("KaiTi"));
            // fontMapper.put("新宋体", PhysicalFonts.get("NSimSun"));
            // fontMapper.put("华文行楷", PhysicalFonts.get("STXingkai"));
            // fontMapper.put("华文仿宋", PhysicalFonts.get("STFangsong"));
            // fontMapper.put("宋体扩展", PhysicalFonts.get("simsun-extB"));
            // fontMapper.put("仿宋", PhysicalFonts.get("FangSong"));
            // fontMapper.put("仿宋_GB2312", PhysicalFonts.get("FangSong_GB2312"));
            // fontMapper.put("幼圆", PhysicalFonts.get("YouYuan"));
            // fontMapper.put("华文宋体", PhysicalFonts.get("STSong"));
            // fontMapper.put("华文中宋", PhysicalFonts.get("STZhongsong"));
            //
            // WordprocessingMLPackage mlPackage = WordprocessingMLPackage.load(isWord);
            // mlPackage.setFontMapper(fontMapper);
            //
            // FOSettings foSettings = Docx4J.createFOSettings();
            // foSettings.setWmlPackage(mlPackage);
            // Docx4J.toFO(foSettings, osPdf, Docx4J.FLAG_EXPORT_PREFER_XSL);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void convertWord2PdfWithSpire(String wordFile, String pdfFile) {
        Document document = new Document();
        document.loadFromFile(wordFile);
        //保存结果文件
        document.saveToFile(pdfFile, FileFormat.PDF);
    }

    public static void convertWord2PdfWithPoi(String wordFile, String pdfFile) {

    }

    public static String docx2Html(String wordFile) throws IOException {
        InputStream inputStream = new FileInputStream(wordFile);
        XWPFDocument xwpfDocument = new XWPFDocument(inputStream);
        // TODO XHTMLOptions 的底层POIXMLDocumentPart包引入有问题
        XHTMLOptions xhtmlOptions = XHTMLOptions.create();

        String picDir = System.getProperty("user.dir") + "/src/main/resources/doc/img";
        xhtmlOptions.setExtractor(new FileImageExtractor(new File(picDir)));
        xhtmlOptions.URIResolver(new BasicURIResolver(picDir));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        XHTMLConverter.getInstance().convert(xwpfDocument, byteArrayOutputStream, xhtmlOptions);
        byteArrayOutputStream.close();
        System.out.println(byteArrayOutputStream.toString());
        return byteArrayOutputStream.toString();
    }
}
