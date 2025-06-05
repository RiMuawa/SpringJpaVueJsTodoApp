package com.fatcatdog.todo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatcatdog.todo.model.Task;
import java.util.List;

import com.fatcatdog.todo.mapper.TaskMapper;

//@Service  brings in appropriate beans
//This class interacts with the database through TaskMapper
@Service
public class TaskService {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskMapper taskMapper;
	

    public TaskService(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    public List<Task> getAllTasks(){

	    logger.info("TaskService getAllTasks");
        return taskMapper.findAll();
	}
	
	public void save(Task task) {

	    logger.info("TaskService save");
	    logger.info("Task: " + task);
        taskMapper.save(task);
	}
	
	public void delete(Task task) {
		logger.info("TaskService delete");
	    logger.info("Task: " + task);
        taskMapper.delete(task);
	}
	
	public Optional<Task> getTask(int id) {
		logger.info("TaskService getTask");
	    logger.info("Task id: " + id);
        return taskMapper.findById(id);
	}

	public Optional<Task> getTaskByCode(int code) {
		logger.info("TaskService getTaskByCode");
	    logger.info("Task code: " + code);
        return taskMapper.findByCode(code);
	}
	
	public Optional<Integer> findMaxCode() {
		logger.info("TaskService getMaxCode");
        Optional<Integer> number = taskMapper.findMaxCode();
	    logger.info("Task number: " + number);
	    
		return number;
	}
	
}
