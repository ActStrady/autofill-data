package com.actstrady.autofilldata.controller;

import com.actstrady.autofilldata.service.FileFullService;
import com.actstrady.autofilldata.utils.FreemarkerReplace;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lenovo
 * @date 2019/11/27
 */
@RestController
@RequiredArgsConstructor
public class FileFullController {
    // 使用构造器注入配合@RequiredArgsConstructor使用不用加构造函数
    private final FileFullService fileFullService;

    @GetMapping(value = "full")
    public void full(HttpServletResponse response) throws IOException, TemplateException {
        // 数据
        Map<String, Object> data = new HashMap<>();
        data.put("titleName", "测试文件");
        data.put("name", "韩寒");
        data.put("age", 23);
        // String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss"));
        data.put("date", LocalDateTime.now());
        String img = System.getProperty("user.dir") + "/autofill-data/src/main/resources/doc/test.png";
        data.put("img", FreemarkerReplace.getImageString(img));
        fileFullService.full(response, data);
    }
}
