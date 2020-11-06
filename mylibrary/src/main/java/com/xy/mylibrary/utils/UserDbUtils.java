package com.xy.mylibrary.utils;

import com.hyphenate.easeui.domain.EaseUser;
import com.xy.mylibrary.base.BaseLibraryApplication;
import com.xy.mylibrary.entity.User;
import com.xy.mylibrary.entity.UserDao;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class UserDbUtils {
    public UserDao mUserdao;
    public static UserDbUtils mUserDbUtils = null;
    private UserDbUtils() {
        mUserdao = new BaseLibraryApplication().mDaoSession.getUserDao();
    }

    public static synchronized UserDbUtils  getIntance(){
        if (mUserDbUtils==null){
            mUserDbUtils = new UserDbUtils();
        }
        return mUserDbUtils;
    }

    /**
     * 获取所有用户
     */
    public  Map<String, EaseUser> selectAlluser(){
        List<User> list = mUserdao.queryBuilder().build().list(); //查询所有用户
        Map<String, EaseUser> users = new Hashtable<String, EaseUser>();
        for (User user : list) {
            EaseUser mEaseUser = new EaseUser(user.username);
            mEaseUser.setAvatar(user.avatar);
            mEaseUser.setNickname(user.nick);
            users.put(user.username,mEaseUser);
        }
        return users;
    }
}
