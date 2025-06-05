package com.fatcatdog.todo.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.fatcatdog.todo.model.Task;

@Repository
public class TaskMapper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final RowMapper<Task> ROW_MAPPER = new BeanPropertyRowMapper<>(Task.class);

    public List<Task> findAll() {
        return jdbcTemplate.query("SELECT id, name, description, due_date, code, status FROM task", ROW_MAPPER);
    }

    public void save(Task task) {
        Integer existing = jdbcTemplate.query("SELECT id FROM task WHERE id=?", new Object[]{task.getId()}, rs -> rs.next() ? rs.getInt(1) : null);
        if (existing == null) {
            jdbcTemplate.update("INSERT INTO task(id, name, description, due_date, code, status) VALUES(?,?,?,?,?,?)",
                    task.getId(), task.getName(), task.getDescription(), task.getDueDate(), task.getCode(), task.isStatus());
        } else {
            jdbcTemplate.update("UPDATE task SET name=?, description=?, due_date=?, code=?, status=? WHERE id=?",
                    task.getName(), task.getDescription(), task.getDueDate(), task.getCode(), task.isStatus(), task.getId());
        }
    }

    public void delete(Task task) {
        jdbcTemplate.update("DELETE FROM task WHERE id=?", task.getId());
    }

    public Optional<Task> findById(int id) {
        List<Task> list = jdbcTemplate.query("SELECT id, name, description, due_date, code, status FROM task WHERE id=?", new Object[]{id}, ROW_MAPPER);
        return list.stream().findFirst();
    }

    public Optional<Task> findByCode(int code) {
        List<Task> list = jdbcTemplate.query("SELECT id, name, description, due_date, code, status FROM task WHERE code=?", new Object[]{code}, ROW_MAPPER);
        return list.stream().findFirst();
    }

    public Optional<Integer> findMaxCode() {
        Integer value = jdbcTemplate.queryForObject("SELECT MAX(code) FROM task", Integer.class);
        return Optional.ofNullable(value);
    }
}
