package com.person.kotlintest.room

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @anthor tr
 * @date 2021/4/28
 * @desc
 */

@Entity(tableName = "table_cache")
class Cache {

    //1.）对于一个表来说，他必须存在一个不为空的主键 也就是必须要标记PrimaryKey和NonNull两个注解
    //PrimaryKey注解的`autoGenerate`属性意味该主键的值，是否由数据库自动生成
    //由于我们这里是字符串的主键key，所以我们想要自己指定他得值，
    //如果是Long,INT类型的主键key，可以选择由数据库自动生成
    @PrimaryKey(autoGenerate = false)
    @NonNull
    var key: String = ""

//    @ColumnInfo(name = "cache_data",defaultValue = "default value")
    var data: String? = null
}