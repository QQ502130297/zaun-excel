package com.temu.zaun.excel.model;

import com.temu.zaun.excel.process.importer.ImportProcessor;

public interface ImportTaskSpecification<QUERY, DATA, VIEW> extends TaskSpec {
    ImportProcessor<QUERY, DATA, VIEW> getProcessor();

    Class<QUERY> getQueryClass();

    Class<DATA> getDataClass();

    Class<VIEW> getViewClass();
}