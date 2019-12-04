package com.actstrady.autofilldata.service;

import com.actstrady.autofilldata.utils.FreemarkerReplace;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Lenovo
 * @date 2019/11/27
 */
@Service
public class FileFullService {
    public void full(HttpServletResponse response, Map data) throws IOException, TemplateException {
        // 模板文件所在目录
        String ftlPath = Objects.requireNonNull(this.getClass().getResource("/doc/")).getPath();
        // 模板文件
        File ftl = new File(ftlPath);
        // 生成的word
        File file = FreemarkerReplace.fullData(ftl, data);

        // 设置返回的数据为word
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/msword");

        // 让浏览器以下载方式处理此文件
        String fileName =  "测试文件" + LocalDateTime.now() + ".doc";
        response.setHeader("Content-Disposition", "attachment;filename="
                .concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));

        // 输出到浏览器
        InputStream inputStream = new FileInputStream(file);
        ServletOutputStream outputStream = response.getOutputStream();
        // 缓存 TODO 读取流可能缺少
        byte[] buffer = new byte[512];
        int bytesToRead = -1;
        // 通过循环将读入的Word文件的内容输出到浏览器中
        while((bytesToRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesToRead);
        }
        inputStream.close();
        outputStream.close();
        // 删除临时文件
        boolean delete = file.delete();
    }
}
