package com.example.buyregister

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context:Context) : SQLiteOpenHelper(context, DATABASE_PRODUCT, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_PRODUCT = "product.db"
        private const val TBL_PRODUCT = "tbl_product"
        private const val ID = "id"
        private const val PRODUCT = "Producto"
        private const val DISTRIBUTOR = "Distribuidor"
        private const val PRIZE = "precio"
    }

    override fun onCreate(db: SQLiteDatabase?) { val createTblProduct = ("CREATE TABLE " + TBL_PRODUCT + "( "
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + PRODUCT + " TEXT,"
                + DISTRIBUTOR + " TEXT," + PRIZE + " TEXT" + ")")
          db?.execSQL(createTblProduct)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
      db?.execSQL("DROP TABLE IF EXISTS $TBL_PRODUCT")
        onCreate(db)
    }



    fun insertProduct(std: ProductModel): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(PRODUCT, std.product)
        contentValues.put(DISTRIBUTOR, std.distributor)
        contentValues.put(PRIZE, std.prize)


        val success = db.insert(TBL_PRODUCT, null, contentValues)
        db.close()
        return success
    }


    @SuppressLint("Range")
    fun getAllProducts(): ArrayList<ProductModel> {

        val stdlist: ArrayList<ProductModel> = ArrayList()
        val selecQuery = "SELECT * FROM $TBL_PRODUCT ORDER BY 1 DESC"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selecQuery, null)

        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selecQuery)
            return ArrayList()
        }

        var id: Int
        var product: String
        var distributor: String
        var prize: String

        if (cursor.moveToFirst()){
           do {

              id = cursor.getInt(cursor.getColumnIndex("id"))
              product = cursor.getString(cursor.getColumnIndex("Producto"))
              distributor = cursor.getString(cursor.getColumnIndex("Distribuidor"))
              prize = cursor.getString(cursor.getColumnIndex("precio"))


            val std = ProductModel(id = id, product = product, distributor = distributor, prize = prize)
            stdlist.add(std)
           } while (cursor.moveToNext())

        }

    return stdlist

    }
    fun updateProduct(std: ProductModel): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(PRODUCT, std.product)
        contentValues.put(DISTRIBUTOR, std.distributor)
        contentValues.put(PRIZE, std.prize)


        val success = db.update(TBL_PRODUCT, contentValues,"id=" + std.id, null)
        db.close()
        return success
    }

    fun deleteProductById(id: Int): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, id)

        val success = db.delete(TBL_PRODUCT, "id=$id", null)
        db.close()
        return success
    }

}

