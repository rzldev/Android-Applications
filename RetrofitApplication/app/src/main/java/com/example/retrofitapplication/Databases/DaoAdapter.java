package com.example.retrofitapplication.Databases;

import android.content.Context;
import android.widget.TextView;

import com.example.retrofitapplication.Adapters.BookmarkAdapter;
import com.example.retrofitapplication.Classes.DaoSession;
import com.example.retrofitapplication.Classes.Mahasiswa;
import com.example.retrofitapplication.Classes.MahasiswaDao;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class DaoAdapter {
    private DaoSession daoSession;

    public DaoAdapter(Context context) {
        this.daoSession = DAODbHelper.getInstance(context);
    }

    public int isMahasiswaExist(String nama) {
        QueryBuilder<Mahasiswa> mahasiswaQueryBuilder = daoSession.queryBuilder(Mahasiswa.class)
                .where(MahasiswaDao.Properties.Nama.eq(nama));
        List<Mahasiswa> mahasiswaList = mahasiswaQueryBuilder.list();

        if (mahasiswaList.size() == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public void insertMahasiswa(String nama, String nim) {
        Mahasiswa m = new Mahasiswa();
        m.setNama(nama);
        m.setNim(nim);
        daoSession.getMahasiswaDao().insert(m);
    }

    public void deleteMahasiswa(String nama) {
        DeleteQuery<Mahasiswa> mahasiswaDeleteQuery = daoSession.queryBuilder(Mahasiswa.class)
                .where(MahasiswaDao.Properties.Nama.eq(nama))
                .buildDelete();
        mahasiswaDeleteQuery.executeDeleteWithoutDetachingEntities();
        daoSession.clear();
    }

    public void getMahasiswa(BookmarkAdapter adapter) {
        List<Mahasiswa> mahasiswaList = daoSession.getMahasiswaDao().queryBuilder().list();
        adapter.addNewData(mahasiswaList);
    }
}
