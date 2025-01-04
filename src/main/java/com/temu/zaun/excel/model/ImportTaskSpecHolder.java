package com.temu.zaun.excel.model;

import com.temu.zaun.excel.process.Processor;
import com.temu.zaun.excel.process.importer.ImportProcessor;
import com.temu.zaun.excel.util.FieldUtil;
import lombok.Data;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Setter
public class ImportTaskSpecHolder<QUERY, DATA, VIEW> implements ImportTaskSpecification<QUERY, DATA, VIEW>{
    private Class<QUERY> queryClass;
    private Class<DATA> dataClass;
    private Class<VIEW> viewClass;
    private String bizCode;
    private String appKey;
    private String taskDesc;
    private Processor processor;
    private List<ColumnHeaders> columnHeaders;
    private Map<String, Field> fieldMap;

    @Override
    public ImportProcessor<QUERY, DATA, VIEW> getProcessor() {
        return (ImportProcessor<QUERY, DATA, VIEW>) processor;
    }

    @Override
    public Class<QUERY> getQueryClass() {
        return queryClass;
    }

    @Override
    public Class<DATA> getDataClass() {
        return dataClass;
    }

    @Override
    public Class<VIEW> getViewClass() {
        return viewClass;
    }

    @Override
    public String getBizCode() {
        return bizCode;
    }

    @Override
    public String getAppKey() {
        return appKey;
    }

    @Override
    public String getTaskDesc() {
        return taskDesc;
    }

    @Override
    public List<ColumnHeaders> getColumnHeaders() {
        return columnHeaders;
    }

    @Override
    public void setColumnHeaders(List<ColumnHeaders> columnHeaders) {
        this.columnHeaders = columnHeaders;
    }

    public Map<String, Field> getFieldMap() {
        return fieldMap;
    }
}
