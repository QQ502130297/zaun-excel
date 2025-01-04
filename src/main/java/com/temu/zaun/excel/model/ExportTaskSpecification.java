package com.temu.zaun.excel.model;

import com.temu.zaun.excel.process.exporter.ExportProcessor;

public interface ExportTaskSpecification<QUERY, DATA, VIEW> extends TaskSpec {
    ExportProcessor<QUERY, DATA, VIEW> getProcessor();

    Class<QUERY> getQueryClass();

    Class<DATA> getDataClass();

    Class<VIEW> getViewClass();
}