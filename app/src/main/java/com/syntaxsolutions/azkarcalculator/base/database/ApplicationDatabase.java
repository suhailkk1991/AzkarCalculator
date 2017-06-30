package com.syntaxsolutions.azkarcalculator.base.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by suhail.kk on 29-09-2016.
 */
@Database(name = ApplicationDatabase.NAME,version = ApplicationDatabase.VERSION)
public class ApplicationDatabase {
    public static final String NAME = "azkarDatabase";

    public static final int VERSION = 1;
}
