package com.example.hello.service;

import com.example.hello.model.BookModel;

import java.util.List;

public interface CRUDService<Model> {

    List<Model> list();
    Model create(Model model);
    Model read(int id);
    Model update(int id, Model model);
    boolean delete(int id);

    BookModel showAvail(int id, BookModel model);

    Model showAvail(int id, Model model);

    Model showAvail();
}
