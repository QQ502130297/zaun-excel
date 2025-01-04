package com.temu.zaun.excel.execute;

import com.alibaba.fastjson.JSON;
import com.temu.zaun.excel.model.ColumnHeaders;
import com.temu.zaun.excel.model.ImportTaskSpecHolder;
import com.temu.zaun.excel.model.TaskInfo;
import com.temu.zaun.excel.process.importer.ImportProcessor;
import com.temu.zaun.excel.util.ClassUtil;
import com.temu.zaun.excel.util.FieldUtil;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.*;

@Data
public class ImportExecute implements Runnable, SubmitOffset{
    private ImportTaskSpecHolder taskSpec;
    private TaskInfo taskInfo;
    @Override
    public void run() {
        try {
            System.out.println(JSON.toJSONString(taskInfo));
            ImportProcessor processor = taskSpec.getProcessor();
            List<Object> list = ClassUtil.newInstance(getData(), getColumnHeaders(), taskSpec.getDataClass(), taskSpec.getFieldMap());
            processor.write(JSON.parseObject(taskInfo.getBizQueryJson(), taskSpec.getQueryClass()), list);
        }catch (Exception e){

        }
    }


    @Override
    public void submitOffset(int offset) {

    }

    private List<ColumnHeaders> getColumnHeaders(){
        List<ColumnHeaders> columnHeaders = taskSpec.getColumnHeaders();
        if(columnHeaders == null){
            return taskSpec.getProcessor().getHeaders(taskInfo.getBizCode());
        }
        return columnHeaders;
    }

    private List<Map<String, String>> getData(){
        Map<String/*表头*/, String/*值*/> data = new HashMap<>();
        data.put("名字", "bear");
        data.put("年龄", "25");
        data.put("地址", "shanghai");
        return Collections.singletonList(data);
    }
}
