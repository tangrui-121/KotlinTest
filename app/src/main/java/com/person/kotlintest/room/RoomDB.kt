package com.person.kotlintest.room

/**
 * @anthor tr
 * @date 2021/4/28
 * @desc
 */
class RoomDB {

    //表名、列名
    const val TABLE_NAME = "table_cache"
    const val COLUMN_NAME_KEY = "cache_key"
    const val COLUMN_NAME_DATA = "cache_data"

    //建表 SQL，是不是很容易犯错，如果使用Java就更容易犯错了
//即使你说这些我信手拈来，但是依然很繁琐
    private const val SQL_CREATE_TABLE_CACHE =
        "CREATE TABLE $TABLE_NAME (" +
                "$ID INTEGER PRIMARY KEY," +
                "$COLUMN_NAME_TITLE TEXT," +
                "$COLUMN_NAME_SUBTITLE TEXT)"

    class CacheDbHelper(context: Context) : SQLiteOpenHelper(context,     DATABASE_NAME, null, DATABASE_VERSION) {
        companion object {
            const val DATABASE_VERSION = 1
            const val DATABASE_NAME = "cache.db"
        }

        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(SQL_CREATE_TABLE_CACHE)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            //根据oldVersion和newVersion编写相应的SQL语句对数据库进行升级
            //在实际应用中，这是比较难处理的一部分，因为缺少必要的验证，极容易出错
        }

        //基类中包含了两个重要的方法 getWritableDatabase() 和   getReadableDatabase()
        //这是我们操作数据库的入口
    }
}