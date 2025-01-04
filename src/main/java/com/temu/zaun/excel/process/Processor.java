package com.temu.zaun.excel.process;

import com.temu.zaun.excel.model.ColumnHeaders;
import com.temu.zaun.excel.model.TaskInfo;

import java.util.List;

public interface Processor {
    List<ColumnHeaders> getHeaders(String bizCode);

    default void afterFinished(TaskInfo var1){}

    default void afterError(TaskInfo var1){}
}
