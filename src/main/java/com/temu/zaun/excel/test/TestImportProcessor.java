package com.temu.zaun.excel.test;

import com.temu.zaun.excel.annocation.ImportSpecification;
import com.temu.zaun.excel.model.ColumnHeaders;
import com.temu.zaun.excel.process.importer.BizImportResult;
import com.temu.zaun.excel.process.importer.ImportProcessor;

import java.util.List;

@ImportSpecification(bizCode = "importPeople", appKey = "test")
public class TestImportProcessor implements ImportProcessor<People, People, People> {
    @Override
    public List<ColumnHeaders> getHeaders(String bizCode) {
        // 业务关联可以查询leo
        return null;
    }

    @Override
    public BizImportResult<People, People> write(People var2, List<People> var3) {
        return null;
    }
}
