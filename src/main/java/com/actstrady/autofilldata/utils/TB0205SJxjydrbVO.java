package com.actstrady.autofilldata.utils;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author ActStrady
 * @date 2019/12/5
 */
@Data
public class TB0205SJxjydrbVO {
    /**
     * 继续教育记录号
     */
    @ExcelProperty(value = "序号")
    private String kidJxjyjlh;
    /**
     * 注册类型
     */
    @ExcelProperty(value = "注册类型")
    private String zclx;
    /**
     * 姓名
     */
    @ExcelProperty(value = "姓名")
    private String xm;
    /**
     * 证件类型
     */
    @ExcelProperty(value = "证件类型")
    private String zjlx;
    /**
     * 证件号 对应证件的号码
     */
    @ExcelProperty(value = "证件号码")
    private String zjhm;
    /**
     * 企业名称
     */
    @ExcelProperty(value = "单位名称")
    private String qymc;
    /**
     * 证书编号
     */
    @ExcelProperty(value = "证书编号")
    private String zsbh;
    /**
     * 印章号
     */
    @ExcelProperty(value = "执业印章号")
    private String kidRyjlh;
    /**
     * 证书有效期
     */
    @ExcelProperty(value = "证书有效期")
    private Date zsyxq;
}
