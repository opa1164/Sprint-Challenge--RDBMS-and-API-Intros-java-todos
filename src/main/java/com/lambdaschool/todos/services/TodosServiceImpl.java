package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.repository.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "todoService")
public class TodosServiceImpl implements TodosService {

    @Autowired
    public TodosRepository todorepos;

    @Autowired
    UserService userService;

    @Override
    public Todos save(long userid, Todos newTodo)
    {
        User currentUser = userService.findUserById(userid);
        Todos saveTodo = new Todos(currentUser, newTodo.getDescription());
        todorepos.save(saveTodo);
        return saveTodo;
    }

    @Override
    public Todos markComplete(long todoid)
    {
        Todos updateTodos = todorepos.findById(todoid).orElseThrow();
        updateTodos.setCompleted(true);
        return todorepos.save(updateTodos);
    }


}