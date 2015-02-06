package com.example.aniolcivit.deuresequips.BBDD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class JugadorHelper extends OrmLiteSqliteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private Dao<JugadorDao, Integer> jugadorDao;

    public JugadorHelper(Context context) {
        super(context, "JUGADORS.db", null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i("JugadorHelper", "onCreate");
            TableUtils.createTable(connectionSource, JugadorDao.class);
        } catch (SQLException e) {
            Log.e("ORM Error", "onCreate Error");
            throw new RuntimeException();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, JugadorDao.class, true);
            TableUtils.createTable(connectionSource, JugadorDao.class);


        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }

    public Dao<JugadorDao, Integer> getDao() throws SQLException {
        jugadorDao = super.getDao(JugadorDao.class);
        return jugadorDao;
    }

    public void clearData() {
        try {
            TableUtils.clearTable(
                    connectionSource, JugadorDao.class);
        } catch (SQLException e) {
        }
    }
}