package com.actstrady.autofilldata.utils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;


class ExcelUtilTest {
    @Test
    public void excelRead() throws FileNotFoundException {
        String fileName = System.getProperty("user.dir") + "/src/main/resources/templates/template.xlsx";
        List<TB0205SJxjydrbVO> vos = new com.stargis.ejjzs.util.ExcelUtil<TB0205SJxjydrbVO>().readData(new File(fileName), new TB0205SJxjydrbVO());
        vos.forEach(System.out::println);
        System.out.println(new com.stargis.ejjzs.util.ExcelUtil<TB0205SJxjydrbVO>().getRow());
    }
}