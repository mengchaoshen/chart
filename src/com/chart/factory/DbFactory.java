package com.chart.factory;

import android.content.Context;
import android.os.Environment;

import com.chart.constant.GlobConstant;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.DbUtils.DaoConfig;
import com.lidroid.xutils.DbUtils.DbUpgradeListener;

public class DbFactory {

	private static DaoConfig daoConfig;

	private static DbUtils intance;

	private static final int DB_VERSION = 1;

	private static final boolean DB_DEBUG = true;

	private static final boolean DB_TRANSACTION = true;

	private static DbUpgradeListener dbUpgradeListener = null;

	public synchronized static DbUtils getIntance(Context context) {
		if (intance != null) {
			return intance;
		}
		daoConfig = new DaoConfig(context);
		daoConfig.setDbName(GlobConstant.DATABASE_NAME);
		daoConfig.setDbVersion(DB_VERSION);
		daoConfig.setDbUpgradeListener(dbUpgradeListener);
		daoConfig.setDbDir(Environment.getExternalStorageDirectory() + "/"
				+ GlobConstant.PACKAGE_NAME + "/"
				+ GlobConstant.DATABASE_DIRECTORY);
		intance = DbUtils.create(daoConfig);
		intance.configDebug(DB_DEBUG);
		intance.configAllowTransaction(DB_TRANSACTION);
		return intance;
	}
}
