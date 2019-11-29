package com.actstrady.autofilldata.utils;

import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class FreemarkerReplaceTest {

    @Test
    void fullData() throws IOException, TemplateException {
        String ftlPath = Objects.requireNonNull(this.getClass().getResource("/doc/")).getPath();
        File ftl = new File(ftlPath);
        Map<String, Object> data = new HashMap<>();
        data.put("titleName", "测试文件");
        data.put("name", "韩寒");
        data.put("age", 23);
        // String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss"));
        data.put("date", LocalDateTime.now());
        String img = System.getProperty("user.dir") + "/src/main/resources/doc/test.png";
        data.put("img", FreemarkerReplace.getImageString(img));
        String path = System.getProperty("user.dir") + "/src/main/resources/doc/result.doc";
        File file = FreemarkerReplace.fullData(ftl, data);
    }
}