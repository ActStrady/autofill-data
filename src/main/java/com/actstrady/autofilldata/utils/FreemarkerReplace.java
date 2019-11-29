package com.actstrady.autofilldata.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author ActStrady
 * @date 2019/11/27
 */
@Slf4j
public class FreemarkerReplace {
    public static File fullData(File tfl, Map data) throws IOException, TemplateException {
        // 创建模板的实例
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_29);
        // 配置编码
        configuration.setDefaultEncoding("UTF-8");
        // 传入文件所在路径
        configuration.setDirectoryForTemplateLoading(tfl);

        // 获取模板
        Template template = configuration.getTemplate("test.ftl", "UTF-8");

        // 输出文件
        String file = System.getProperty("user.dir") + "/src/main/resources/doc/result.doc";
        File outFile = new File(file);
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),
                StandardCharsets.UTF_8), 10240);

        // 按照对应关系存入
        template.process(data, writer);
        writer.close();
        return outFile;
    }

    public static String getImageString(String img) throws IOException {
        byte[] data = null;
        File file;
        try (InputStream inputStream = new FileInputStream(img)) {
            int count = 0;
            while (count == 0) {
                count = inputStream.available();
            }
            data = new byte[count];
            int readCount = 0;
            while (readCount < count) {
                readCount += inputStream.read(data, readCount, count - readCount);
            }
        } catch (IOException e) {
            log.error("error:", e);
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return data != null ? encoder.encode(data) : "";
    }
}
