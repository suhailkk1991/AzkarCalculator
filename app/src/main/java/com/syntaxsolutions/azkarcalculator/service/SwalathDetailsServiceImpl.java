package com.syntaxsolutions.azkarcalculator.service;

import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.syntaxsolutions.azkarcalculator.base.database.SwalathDetailsTable;
import com.syntaxsolutions.azkarcalculator.base.database.SwalathDetailsTable$Table;


import java.util.List;

/**
 * Created by Suhail k k on 22-04-2017.
 */

public class SwalathDetailsServiceImpl implements SwalathDetailsService {
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
    public List getAll(Object o) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public List getAll(long id) {

        return new Select().from(SwalathDetailsTable.class)
                .where(
                        Condition.column(SwalathDetailsTable$Table.HEADERID).is(id)).queryList();

    }

    @Override
    public List getAll(int id1, long id2) {
        return null;
    }
}
