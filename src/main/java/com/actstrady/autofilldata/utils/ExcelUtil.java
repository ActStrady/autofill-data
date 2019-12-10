package com.stargis.ejjzs.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * excel 工具类
 *
 * @author ActStrady
 * @date 2019/12/
 */
@Slf4j
public class ExcelUtil<T> {
    /**
     * 返回的封装数据
     */
    private List<T> list = new ArrayList<>();

    /**
     * 是否成功标识
     */
    private boolean result = true;

    /**
     * 当前行数
     */
    private static Integer rowIndex = 0;

    /**
     * 由于是异步读取数据，故需要一个监听器
     */
    private class DataListener extends AnalysisEventListener<T> {
        /**
         * 读取并保存数据
         *
         * @param t       其中的一条数据
         * @param context 上下文一般不用
         */
        @Override
        public void invoke(T t, AnalysisContext context) {
            Method[] declaredMethods = t.getClass().getDeclaredMethods();
            for (Method declaredMethod : declaredMethods) {
                if (declaredMethod.getName().contains("get")) {
                    try {
                        Object object = declaredMethod.invoke(t);
                        if (object == null) {
                            result = false;
                            list.clear();
                            break;
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        log.error("excel解析内部错误：{}", e.getMessage());
                    }
                }
            }
            if (result) {
                rowIndex = context.readRowHolder().getRowIndex() + 2;
                list.add(t);
            }
        }

        /**
         * 所有数据解析完成了 都会来调用
         *
         * @param context 上下文
         */
        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
        }
    }

    /**
     * 读取数据
     *
     * @param t        数据映射
     */
    public List<T> readData(InputStream inputStream, T t) {
        EasyExcel.read(inputStream, t.getClass(), new DataListener()).sheet().doRead();
        if (result) {
            return list;
        } else {
            return new ArrayList<>();
        }
    }
    /**
     * 读取数据
     *
     * @param file 文件
     * @param t        数据映射
     */
    public List<T> readData(File file, T t) {
        EasyExcel.read(file, t.getClass(), new DataListener()).sheet().doRead();
        if (result) {
            return list;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * 查看读到第几行
     */
    public Integer getRow() {
        return rowIndex;
    }

}