package com.tiago.annoter.data.repository

import com.tiago.annoter.data.local.TaskDao
import com.tiago.annoter.data.local.TaskEntity
import com.tiago.annoter.data.mapper.toTaskEntity
import com.tiago.annoter.domain.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {
    fun getAll(): Flow<List<TaskEntity>> = taskDao.getAll()

    suspend fun insertTask(task: TaskModel) = taskDao.insertTask(task.toTaskEntity())

    suspend fun updateTask(task: TaskModel) = taskDao.updateTask(task.toTaskEntity())

    suspend fun deleteTask(task: TaskModel) = taskDao.deleteTask(task.toTaskEntity())
}