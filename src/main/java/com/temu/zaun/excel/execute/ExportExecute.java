package com.temu.zaun.excel.execute;

import com.alibaba.fastjson.JSON;
import com.temu.zaun.excel.model.ExportTaskSpecHolder;
import com.temu.zaun.excel.model.TaskInfo;
import com.temu.zaun.excel.model.TaskSpec;
import lombok.Data;

@Data
public class ExportExecute implements Runnable, SubmitOffset{

    private ExportTaskSpecHolder taskSpec;
    private TaskInfo taskInfo;

    @Override
    public void run() {
        System.out.println(JSON.toJSONString(taskInfo) + "," + JSON.toJSONString(taskSpec));
    }


    @Override
    public void submitOffset(int offset) {

    }
}
