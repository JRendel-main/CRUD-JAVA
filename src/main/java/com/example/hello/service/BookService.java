package com.example.hello.service;

import com.example.hello.model.BookModel;
import com.example.hello.repository.CRUDrepository;

import java.util.List;

public class BookService implements CRUDService<BookModel> {
    private CRUDrepository<BookModel> repository;

    public BookService(CRUDrepository<BookModel> repository) {
        this.repository = repository;
    }

    @Override
    public List<BookModel> list() {
        return repository.list();
    }

    @Override
    public BookModel create(BookModel model) {
        return repository.create(model);
    }

    @Override
    public BookModel read(int id) {
        return repository.read(id);
    }

    @Override
    public BookModel update(int id, BookModel model) {
        return repository.update(id, model);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }
}
