package com.chart.dao;

import java.util.List;

import android.content.Context;

import com.chart.factory.DbFactory;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.SqlInfo;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.db.table.DbModel;
import com.lidroid.xutils.exception.DbException;

public class BaseDao {
	public static void insert(Context context, Object object) throws DbException {
        if (object != null) {
            DbFactory.getIntance(context).save(object);
        }
    }

    public static void insertAll(Context context, List<?> list) throws DbException {
        if (list != null) {
            DbFactory.getIntance(context).saveAll(list);
        }
    }

    public static boolean update(Context context, Object object, WhereBuilder whereBuilder, String... updateColumnNames) throws DbException {
        if (object != null) {
            DbFactory.getIntance(context).update(object, whereBuilder, updateColumnNames);
        }
        return true;
    }

    public static List<?> query(Context context, Selector selector) throws DbException {
        List<?> list = DbFactory.getIntance(context).findAll(selector);
        return list;
    }

    public static boolean delete(Context context, Class<?> c, WhereBuilder whereBuilder) throws DbException {
        DbFactory.getIntance(context).delete(c, whereBuilder);
        return true;
    }

    public static boolean deleteAll(Context context, Class<?> c) throws DbException {
        DbFactory.getIntance(context).deleteAll(c);
        return true;
    }

    public static List<DbModel> query(Context context, String sql) throws DbException {
        return DbFactory.getIntance(context).findDbModelAll(new SqlInfo(sql));
    }

    public static Object queryFirst(Context context, Selector selector) throws DbException {
        return DbFactory.getIntance(context).findFirst(selector);
    }

}
