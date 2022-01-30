package com.example.hello.repository;

import java.util.List;

public interface CRUDrepository<Entity> {

    List<Entity> list();
    Entity create(Entity entity);
    Entity read(int id);
    Entity update(int id, Entity entity);
    boolean delete(int id);
}
