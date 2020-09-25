package com.example.mlakumlaku.Adapters;

import android.content.Context;

import com.example.mlakumlaku.Classes.DaoSession;
import com.example.mlakumlaku.Classes.User;
import com.example.mlakumlaku.Classes.UserDao;
import com.example.mlakumlaku.Databases.DaoHelper;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class DaoAdapter {

    private Context context;
    private DaoSession daoSession;

    public DaoAdapter(Context context) {
        this.context = context;
        this.daoSession = DaoHelper.getInstance(context);
    }

    public void saveUser(int id, String email, String password) {
        QueryBuilder<User> userQueryBuilder = daoSession.queryBuilder(User.class)
                .where(UserDao.Properties.Id.eq(id));
        List<User> userList = userQueryBuilder.list();

        if (userList.size() > 0) {

        } else {
            User u = new User();
            u.setId(id);
            u.setEmail(email);
            u.setPassword(password);

            daoSession.getUserDao().insert(u);

        }

    }

    public List<User> showUser() {
        List<User> userList = daoSession.queryBuilder(User.class).limit(1).list();
        return userList;
    }

    public void deleteUser(int id) {
        DeleteQuery<User> userDeleteQuery = daoSession.queryBuilder(User.class)
                .where(UserDao.Properties.Id.eq(id))
                .buildDelete();
        daoSession.clear();
    }

}
