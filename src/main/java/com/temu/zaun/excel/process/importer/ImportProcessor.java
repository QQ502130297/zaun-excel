package com.temu.zaun.excel.process.importer;

import com.temu.zaun.excel.process.Processor;

import java.util.List;

public interface ImportProcessor<QUERY, DATA, VIEW> extends Processor {
    BizImportResult<VIEW, DATA> write(QUERY var2, List<DATA> var3);
}
