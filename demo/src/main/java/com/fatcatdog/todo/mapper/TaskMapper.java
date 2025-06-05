package com.fatcatdog.todo.mapper;

import org.springframework.stereotype.Component;

import com.fatcatdog.todo.dto.TaskDto;
import com.fatcatdog.todo.entity.Task;

@Component
public class TaskMapper {

    public TaskDto toDto(Task task) {
        if (task == null) {
            return null;
        }
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setDescription(task.getDescription());
        dto.setDueDate(task.getDueDate());
        dto.setCode(task.getCode());
        dto.setStatus(task.isStatus());
        return dto;
    }

    public Task toEntity(TaskDto dto) {
        if (dto == null) {
            return null;
        }
        Task task = new Task();
        task.setId(dto.getId());
        task.setName(dto.getName());
        task.setDescription(dto.getDescription());
        task.setDueDate(dto.getDueDate());
        task.setCode(dto.getCode());
        task.setStatus(dto.isStatus());
        return task;
    }
}
