package com.holiday.jetpackstudy.room.dao;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.holiday.jetpackstudy.room.entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(User... users);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateUsers(User... users);

    @Delete
    void deleteUsers(User... users);

    //@Query在编译的时候会验证准确性，所以如果查询出现问题在编译的时候就会报错。
    @Query("SELECT * FROM t_user")
    List<User> queryUsers();

    @Query("SELECT * FROM t_user WHERE id = :id")
    List<User> queryUserById(int id);

    @Query("SELECT * FROM t_user WHERE id BETWEEN :startId AND :endId")
    List<User> queryUserBetweenId(int startId, int endId);

    @Query("SELECT * FROM t_user WHERE id IN (:ids)")
    LiveData<List<User>> queryUserInIds(List<Integer> ids);

    /**
     * 进入这个方法的具体实现，可以看到new LimitOffsetDataSource
     *
     * @see androidx.room.paging.LimitOffsetDataSource#loadRange(int, int)
     */
    @Query("SELECT * FROM t_user")
    DataSource.Factory<Integer, User> queryUsersLive();
}
