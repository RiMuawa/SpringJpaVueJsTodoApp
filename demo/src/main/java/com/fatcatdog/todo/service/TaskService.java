package com.fatcatdog.todo.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatcatdog.todo.entity.Task;
import com.fatcatdog.todo.dao.TaskDao;

//@Service  brings in appropriate beans
//This class brings in an instance of our TaskDao to add more abstraction between controller and database
@Service
public class TaskService {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

	@Autowired
        private TaskDao taskDao;
	

        public TaskService(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

	public Iterable<Task> getAllTasks(){

	    logger.info("TaskService getAllTasks");
                return taskDao.findAll();
	}
	
	public void save(Task task) {

	    logger.info("TaskService save");
	    logger.info("Task: " + task);
                taskDao.save(task);
	}
	
	public void delete(Task task) {
		logger.info("TaskService delete");
	    logger.info("Task: " + task);
                taskDao.delete(task);
	}
	
	public Optional<Task> getTask(int id) {
		logger.info("TaskService getTask");
	    logger.info("Task id: " + id);
            return taskDao.findById(id);
	}

	public Optional<Task> getTaskByCode(int code) {
		logger.info("TaskService getTaskByCode");
	    logger.info("Task code: " + code);
            return taskDao.findByCode(code);
	}
	
	public Optional<Integer> findMaxCode() {
		logger.info("TaskService getMaxCode");
                Optional<Integer> number = taskDao.findMaxCode();
	    logger.info("Task number: " + number);
	    
		return number;
	}
	
}
