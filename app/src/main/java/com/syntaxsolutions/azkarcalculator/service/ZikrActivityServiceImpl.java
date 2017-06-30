package com.syntaxsolutions.azkarcalculator.service;

import com.raizlabs.android.dbflow.sql.language.Select;
import com.syntaxsolutions.azkarcalculator.base.database.ZikrHeaderTable;

import java.util.List;

/**
 * Created by lenovo on 06-01-2017.
 */

public class ZikrActivityServiceImpl implements ZikrActivityService<ZikrHeaderTable> {

    @Override
    public List<ZikrHeaderTable> getAll() {
        return new Select().from(ZikrHeaderTable.class).queryList();
    }

    @Override
    public List<ZikrHeaderTable> getAll(long id) {
        return null;
    }

    @Override
    public List<ZikrHeaderTable> getAll(int id1, long id2) {
        return null;
    }


    @Override
    public int remove() {
        return 0;
    }

    @Override
    public int remove(long id) {

        return 0;
    }

    @Override
    public int update() {
        return 0;
    }

    @Override
    public int update(long id) {
        return 0;
    }

    @Override
    public List getAll(int id) {
        return null;
    }

    @Override
    public List getAll(String id) {
        return null;
    }

    @Override
    public List getAll(int id1, int id2) {
        return null;
    }

    @Override
    public List<ZikrHeaderTable> getAll(ZikrHeaderTable zikrHeaderTable) {
        return null;
    }


}
