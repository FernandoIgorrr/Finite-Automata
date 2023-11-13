package com.fstates.beheavor;

import com.fstates.object.entity.Entity;

public interface Task {
    TaskState execute(Entity entity);
}
