package com.temu.zaun.excel.params;

import lombok.Data;

@Data
public class ExportDataParams {
    private Object query;
    private String appKey;
    private String bizCode;
}
