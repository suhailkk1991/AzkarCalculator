package com.syntaxsolutions.azkarcalculator.service;

import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.syntaxsolutions.azkarcalculator.base.database.ZikrDetailsTable;
import com.syntaxsolutions.azkarcalculator.base.database.ZikrDetailsTable$Table;

import java.util.List;

/**
 * Created by lenovo on 14-01-2017.
 */

public class ZikrDetailsServiceImpl implements ZikrDetailsService<ZikrDetailsTable> {


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
    public List<ZikrDetailsTable> getAll(int id) {

        return null;
    }

    @Override
    public List<ZikrDetailsTable> getAll(String id) {
        return null;
    }

    @Override
    public List<ZikrDetailsTable> getAll(int id1, int id2) {
        return null;
    }

    @Override
    public List<ZikrDetailsTable> getAll(ZikrDetailsTable zikrDetailsTable) {
        return null;
    }

    @Override
    public List<ZikrDetailsTable> getAll() {
        return null;
    }

    @Override
    public List<ZikrDetailsTable> getAll(long id) {
        return new Select().from(ZikrDetailsTable.class)
                .where(
                        Condition.column(ZikrDetailsTable$Table.HEADERID).is(id)).queryList();

    }

    @Override
    public List<ZikrDetailsTable> getAll(int id1, long id2) {

        return null;
    }
}
