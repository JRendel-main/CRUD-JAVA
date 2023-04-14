package com.example.hello.repository;

import com.example.hello.model.BookModel;

import java.util.List;

public interface CRUDrepository<Entity> {

    List<Entity> list();
    Entity create(Entity entity);
    Entity read(int id);
    Entity update(int id, Entity entity);

    BookModel showAvail();

    boolean delete(int id);

    Entity showAvail(int id, Entity model);
}
