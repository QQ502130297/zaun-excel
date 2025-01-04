package com.temu.zaun.excel.process.exporter;

import com.temu.zaun.excel.process.Processor;
import java.util.List;

public interface ExportProcessor<QUERY, DATA, VIEW> extends Processor {

    Integer totalCount(QUERY var2);

    List<DATA> queryData(QUERY var2, int offset, int pageSize);

    List<VIEW> convert(QUERY var2, List<DATA> var3);
}
