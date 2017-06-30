package com.syntaxsolutions.azkarcalculator.service;

import com.raizlabs.android.dbflow.sql.language.Select;
import com.syntaxsolutions.azkarcalculator.base.database.QathmHeaderTable;

import java.util.List;

/**
 * Created by lenovo on 16-01-2017.
 */

public class QathmServiceImpl implements QathmService<QathmHeaderTable> {
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
    public List<QathmHeaderTable> getAll(int id) {
        return null;
    }

    @Override
    public List<QathmHeaderTable> getAll(String id) {
        return null;
    }

    @Override
    public List<QathmHeaderTable> getAll(int id1, int id2) {
        return null;
    }

    @Override
    public List<QathmHeaderTable> getAll(QathmHeaderTable qathmHeaderTable) {
        return null;
    }

    @Override
    public List<QathmHeaderTable> getAll() {
        return new Select().from(QathmHeaderTable.class).queryList();
    }

    @Override
    public List<QathmHeaderTable> getAll(long id) {
        return null;
    }

    @Override
    public List<QathmHeaderTable> getAll(int id1, long id2) {
        return null;
    }
}
