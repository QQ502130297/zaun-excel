package com.temu.zaun.excel.model;

import lombok.Data;

@Data
public class TaskInfo {
    private String bizQueryJson;
    private String taskId;
    private String bizCode;
    private String appKey;
    private Integer dataTotalCount;
    private Integer dataProcessedCount;
    private Integer dataSuccessCount;
    private String status;
    private Integer taskType;
}
