package com.temu.zaun.excel.process.importer;

import java.util.List;

public interface BizImportResult<VIEW, DATA> {
    List<VIEW> getView();

    List<DATA> getData();
}

