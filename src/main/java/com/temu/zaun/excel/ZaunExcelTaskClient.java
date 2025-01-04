package com.temu.zaun.excel;

import com.temu.zaun.excel.execute.ExportExecute;
import com.temu.zaun.excel.execute.ImportExecute;
import com.temu.zaun.excel.model.*;
import com.temu.zaun.excel.params.ExportDataParams;
import com.temu.zaun.excel.params.ImportDataParams;
import com.temu.zaun.excel.process.Processor;
import com.temu.zaun.excel.specification.ExportSpecificationResolver;
import com.temu.zaun.excel.specification.ImportSpecificationResolver;
import com.temu.zaun.excel.specification.TaskSpecificationResolver;

import java.util.*;
import java.util.concurrent.*;

public class ZaunExcelTaskClient {
    private static Map<String/*bizCode*/, TaskSpec> taskSpecMap = new HashMap<>();
    private static List<TaskSpecificationResolver> resolvers = Arrays.asList(new ExportSpecificationResolver<>(), new ImportSpecificationResolver<>());
    private static ExecutorService exportExecutor = Executors.newFixedThreadPool(50);
    private static ExecutorService importExecutor = Executors.newFixedThreadPool(50);

    // 项目启动时候去拉取不重复的任务，且未执行的任务
    // 服务端控制 长时间未执行成功则置为失败
    private static BlockingQueue<TaskInfo> taskInfos = new ArrayBlockingQueue<>(1000);

    static {
        new Thread(ZaunExcelTaskClient::loopExecute).start();
    }

    private static void loopExecute(){
        // dingshi1renwu1yexing
        while (true){
            try {
                TaskInfo taskInfo = taskInfos.take();
                TaskSpec taskSpec = taskSpecMap.get(taskInfo.getBizCode());
                if(taskInfo.getTaskType() == 0){
                    ExportExecute execute = new ExportExecute();
                    execute.setTaskInfo(taskInfo);
                    execute.setTaskSpec((ExportTaskSpecHolder) taskSpec);
                    exportExecutor.submit(execute);
                }else{
                    ImportExecute execute = new ImportExecute();
                    execute.setTaskInfo(taskInfo);
                    execute.setTaskSpec((ImportTaskSpecHolder) taskSpec);
                    importExecutor.submit(execute);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public synchronized static boolean registerProcessor(Class<? extends Processor> processorClazz, List<ColumnHeaders> columnHeaders){
        try {
            Processor processor = processorClazz.newInstance();
            for (TaskSpecificationResolver resolver : resolvers) {
                if(resolver.canResolve(processor)){
                    TaskSpec resolve = resolver.resolve(processor);
                    resolve.setColumnHeaders(columnHeaders);
                    taskSpecMap.put(resolve.getBizCode(), resolve);
                    return true;
                }
            }
        } catch (Exception e){

        }
        return false;
    }

    public  static boolean registerProcessor(Class<? extends Processor> processorClazz){
        return registerProcessor(processorClazz, null);
    }

    public static String importData(ImportDataParams importDataParams) throws Exception {
        // 注册一个任务 远程调用dubbo 创建一个task
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTaskType(1);
        taskInfo.setBizCode(importDataParams.getBizCode());
        taskInfo.setTaskId("1");
        taskInfo.setBizQueryJson(importDataParams.getQueryJson());
        taskInfos.put(taskInfo);
        return taskInfo.getTaskId();
    }

    public static String exportData(ExportDataParams exportDataParams) throws Exception {
        // 注册一个任务
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTaskType(1);
        taskInfo.setBizCode(exportDataParams.getBizCode());
        taskInfo.setTaskId("1");
        taskInfos.put(taskInfo);
        return taskInfo.getTaskId();
    }

}
