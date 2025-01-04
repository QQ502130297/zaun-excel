package com.temu.zaun.excel.model;

import java.util.List;

public interface TaskSpec {
    String getBizCode();
    String getAppKey();
    String getTaskDesc();
    List<ColumnHeaders> getColumnHeaders();
    void setColumnHeaders(List<ColumnHeaders> columnHeaders);
}
