package com.aulaudemy.tasklist.helper;

import com.aulaudemy.tasklist.model.Task;

import java.util.List;

public interface iTaskDAO {
    public boolean create(Task task);
    public List<Task> read();
    public boolean update(Task task);
    public boolean delete(Task task);

}
