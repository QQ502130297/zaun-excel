package com.temu.zaun.excel.params;

import lombok.Data;

@Data
public class ImportDataParams {
    private String aviUrl;
    private String sfsUuid;
    private String bizCode;
    private String queryJson;
}
