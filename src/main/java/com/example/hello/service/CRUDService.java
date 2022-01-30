package com.example.hello.service;

import java.util.List;

public interface CRUDService<Model> {

    List<Model> list();
    Model create(Model model);
    Model read(int id);
    Model update(int id, Model model);
    boolean delete(int id);
}
