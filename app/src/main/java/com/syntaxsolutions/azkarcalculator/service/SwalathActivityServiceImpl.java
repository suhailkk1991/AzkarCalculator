package com.syntaxsolutions.azkarcalculator.service;

import com.raizlabs.android.dbflow.sql.language.Select;
import com.syntaxsolutions.azkarcalculator.base.database.SwalathHeaderTable;

import java.util.List;

/**
 * Created by lenovo on 16-01-2017.
 */

public class SwalathActivityServiceImpl implements SwalathActivityService<SwalathHeaderTable> {

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
    public List<SwalathHeaderTable> getAll(int id) {
        return null;
    }

    @Override
    public List<SwalathHeaderTable> getAll(String id) {
        return null;
    }

    @Override
    public List<SwalathHeaderTable> getAll(int id1, int id2) {
        return null;
    }

    @Override
    public List<SwalathHeaderTable> getAll(SwalathHeaderTable swalathHeaderTable) {
        return null;
    }

    @Override
    public List<SwalathHeaderTable> getAll() {

        return new Select().from(SwalathHeaderTable.class).queryList();
    }

    @Override
    public List<SwalathHeaderTable> getAll(long id) {
        return null;
    }

    @Override
    public List<SwalathHeaderTable> getAll(int id1, long id2) {
        return null;
    }
}
