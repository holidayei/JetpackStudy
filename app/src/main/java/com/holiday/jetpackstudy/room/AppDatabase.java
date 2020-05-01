package com.holiday.jetpackstudy.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.holiday.jetpackstudy.room.dao.UserDao;
import com.holiday.jetpackstudy.room.entity.User;

@Database(entities = {User.class}, version = 1)
//@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "room_app.db";

    public abstract UserDao userDao();

}
