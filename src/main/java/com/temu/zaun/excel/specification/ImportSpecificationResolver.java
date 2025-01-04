package com.temu.zaun.excel.specification;

import com.temu.zaun.excel.annocation.ImportSpecification;
import com.temu.zaun.excel.model.ExportTaskSpecHolder;
import com.temu.zaun.excel.model.ImportTaskSpecHolder;
import com.temu.zaun.excel.model.TaskSpec;
import com.temu.zaun.excel.process.Processor;
import com.temu.zaun.excel.process.importer.ImportProcessor;
import com.temu.zaun.excel.util.FieldUtil;
import com.temu.zaun.excel.util.TypeUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ImportSpecificationResolver<QUERY, DATA, VIEW> implements TaskSpecificationResolver {

    @Override
    public TaskSpec resolve(Processor processor) {
        Class<? extends Processor> processorClass = processor.getClass();
        ImportSpecification specAnnotation = processorClass.getAnnotation(ImportSpecification.class);
        List<Type> genericParamType = TypeUtil.getGenericParamType(processorClass, ImportProcessor.class);
        Class<QUERY> queryClass = (Class)genericParamType.get(0);
        Class<DATA> dataClass = genericParamType.size() > 1 ? (Class)genericParamType.get(1) : null;
        Class<VIEW> viewClass = genericParamType.size() > 2 ? (Class)genericParamType.get(2) : null;
        ImportTaskSpecHolder<QUERY, DATA, VIEW> holder = new ImportTaskSpecHolder();
        holder.setProcessor(processor);
        holder.setQueryClass(queryClass);
        holder.setDataClass(dataClass);
        holder.setViewClass(viewClass);
        holder.setAppKey(specAnnotation.appKey());
        holder.setTaskDesc(specAnnotation.desc());
        holder.setBizCode(specAnnotation.bizCode());

        if(dataClass != null){
            List<Field> allFields = FieldUtil.getAllFields(dataClass);
            Map<String, Field> collect = allFields.stream().collect(Collectors.toMap(Field::getName, f -> f));
            holder.setFieldMap(collect);
        }

        return holder;
    }

    @Override
    public boolean canResolve(Processor var1) {
        if (var1 instanceof ImportProcessor) {
            return true;
        }
        return false;
    }
}