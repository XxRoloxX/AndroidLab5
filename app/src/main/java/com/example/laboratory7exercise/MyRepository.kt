package com.example.laboratory7exercise

import android.content.Context

class MyRepository(context: Context) {
    private var dataList: MutableList<DBItem>? = null
    private lateinit var myDao: MyDao
    private lateinit var db: MyDB

    companion object {
        private var R_INSTANCE: MyRepository? = null
        fun getInstance(context: Context):MyRepository {
            if (R_INSTANCE == null){
                R_INSTANCE = MyRepository(context)
            }
            return R_INSTANCE as MyRepository
        }
    }
    init {
        db = MyDB.getDatabase(context)!!
        myDao = db.myDao()!!
//        addItem(DBItem("hello", "lastName", 2,false,3F))
    }

    fun getData(): MutableList<DBItem>? {
        dataList = myDao.getAllData()
        return dataList
    }
    fun findById(id: Int): DBItem?{
        return myDao.findById(id)
    }

    fun addItem(item: DBItem) : Boolean {
        return myDao.insert(item) >= 0
    }

    fun modifyItem(item: DBItem) {
        return myDao.update(item)
    }

    fun deleteItem(item: DBItem) : Boolean {
        return myDao.delete(item) > 0
    }

}