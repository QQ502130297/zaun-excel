package com.temu.zaun.excel.specification;

import com.temu.zaun.excel.model.TaskSpec;
import com.temu.zaun.excel.process.Processor;

public interface TaskSpecificationResolver {
    TaskSpec resolve(Processor var1);

    boolean canResolve(Processor var1);
}
