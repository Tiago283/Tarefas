package com.tiago.annoter.data.mapper

import com.tiago.annoter.data.local.TaskEntity
import com.tiago.annoter.domain.model.TaskModel

fun TaskModel.toTaskEntity() : TaskEntity = TaskEntity(
    id = id,
    isChecked = isChecked,
    task = task
)

fun TaskEntity.toTaskModel() : TaskModel = TaskModel(
    id = id,
    isChecked = isChecked,
    task = task
)