package com.temu.zaun;

import com.alibaba.fastjson.JSON;
import com.temu.zaun.excel.ZaunExcelTaskClient;
import com.temu.zaun.excel.model.ColumnHeaders;
import com.temu.zaun.excel.params.ImportDataParams;
import com.temu.zaun.excel.test.People;
import com.temu.zaun.excel.test.TestImportProcessor;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        People people = new People();
        people.setName("bear");
        people.setAge(25);
        people.setAddress("shanghai");

        List<ColumnHeaders> list = new ArrayList<>();
        ColumnHeaders name = new ColumnHeaders();
        name.setFieldName("name");
        name.setHeadName("名字");
        list.add(name);

        ColumnHeaders age = new ColumnHeaders();
        age.setFieldName("age");
        age.setHeadName("年龄");
        list.add(age);

        ColumnHeaders address = new ColumnHeaders();
        address.setFieldName("address");
        address.setHeadName("地址");
        list.add(address);
        ZaunExcelTaskClient.registerProcessor(TestImportProcessor.class, list);

        ImportDataParams importDataParams = new ImportDataParams();
        importDataParams.setBizCode("importPeople");
        importDataParams.setQueryJson(JSON.toJSONString(people));
        ZaunExcelTaskClient.importData(importDataParams);
    }
}
