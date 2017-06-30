package com.syntaxsolutions.azkarcalculator.service;

import java.util.List;

/**
 * Created by lenovo on 07-01-2017.
 */

public interface ServiceBase<T> {

    int remove();

    int remove(long id);

    int update();

    int update(long id);

    List<T> getAll(int id);

    List<T> getAll(String id);

    List<T> getAll(int id1, int id2);

    List<T> getAll(T t);

    List<T> getAll();

    List<T> getAll(long id);

    List<T> getAll(int id1, long id2);


}
