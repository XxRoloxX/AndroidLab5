package com.example.laboratory7exercise

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MyDao {
    @Query("SELECT * FROM  item_table ORDER BY id ASC")
    fun getAllData(): MutableList<DBItem>?

    @Query("SELECT * FROM item_table WHERE id = :id")
    fun findById(id: Int):DBItem?

    @Query("DELETE FROM item_table")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item:DBItem): Long

    @Delete
    fun delete(item: DBItem): Int

    @Update
    fun update(item:DBItem)
}