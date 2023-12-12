package com.example.laboratory7exercise

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Origin{
    Europe,
    America,
    Asia
}

@Entity(tableName = "item_table")
class DBItem {
    @PrimaryKey(autoGenerate = true)
    var id = 0;
    var mainName: String = "";
    var latinName:String ="";

    @ColumnInfo(name = "quality")
    var quality = 0F
    var price = 0;
    var isFruit = false;

    var origin:Origin = Origin.Europe
    constructor(mainName:String, latinName:String, price:Int, isFruit:Boolean, quality: Float, origin: Origin){
        this.mainName = mainName
        this.latinName = latinName
        this.price = price
        this.isFruit = isFruit
        this.quality = quality
        this.origin = origin
    }

}