package com.xy.mylibrary.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.xy.mylibrary.entity.DaoMaster;
import com.xy.mylibrary.entity.DaoSession;

public class BaseLibraryApplication extends Application {
    private DaoMaster.DevOpenHelper mHelper = null;
    private SQLiteDatabase db = null;
    private DaoMaster mDaoMaster= null;
    public DaoSession mDaoSession = null;
    private BaseLibraryApplication mBaseLibraryApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        mBaseLibraryApplication = this;
        setDatabase();
    }

    public BaseLibraryApplication  getIntance(){
        return mBaseLibraryApplication;
    }

    /**
     * 初始化数据库
     * 只能在对应依赖的moudle中 初始化数据库不然会报错
     */
    private void setDatabase(){
        mHelper = new  DaoMaster.DevOpenHelper(this, "xy-db", null);

        db = mHelper.getWritableDatabase();
                mDaoMaster =  new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }}
