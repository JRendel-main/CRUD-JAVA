package com.example.hello.repository;

import java.util.List;

public interface CRUDrepository<Entity> {

    List<Entity> list();
    Entity create(Entity entity);
    Entity read(Entity entity);
    Entity update(Entity entity);
    boolean delete(int id);
}
