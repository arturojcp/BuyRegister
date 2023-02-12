package com.example.buyregister

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class SQLiteHelperC(context: Context) : SQLiteOpenHelper(context, DATABASE_BUYER, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_BUYER = "buyer.db"
        private const val TBL_BUYER = "tbl_buyer"
        private const val ID = "id"
        private const val NAMEBUYER = "namebuyer"
        private const val CI = "ci"
        private const val QUANTITY = "quantity"
        private const val TOTALP = "cantidadP"
        private const val METOD = "metod"
        private const val DATE = "date"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblBuyer = ("CREATE TABLE " + TBL_BUYER + "( "
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAMEBUYER + " TEXT,"
                + CI + " TEXT," + QUANTITY + " TEXT," + TOTALP + " TEXT," + METOD + " TEXT,"
                + DATE +  " TEXT" + ")")
        db?.execSQL(createTblBuyer)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TBL_BUYER")
        onCreate(db)
    }


    fun insertBuy(std: BuyerModel): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(NAMEBUYER, std.NAMEBUYER)
        contentValues.put(CI, std.CI)
        contentValues.put(QUANTITY, std.QUANTITY)
        contentValues.put(TOTALP, std.TOTALP)
        contentValues.put(METOD, std.METOD)
        contentValues.put(DATE, std.DATE)

        val success = db.insert(TBL_BUYER, null, contentValues)
        db.close()
        return success
    }


    @SuppressLint("Range")
    fun getAllBuy(): ArrayList<BuyerModel> {

        val stdlist: ArrayList<BuyerModel> = ArrayList()
        val selecQuery = "SELECT * FROM $TBL_BUYER ORDER BY 1 DESC"
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
        var namebuyer: String
        var ci: String
        var quantity: String
        var totalp: String
        var metod: String
        var date: String

        if (cursor.moveToFirst()) {
            do {

                id = cursor.getInt(cursor.getColumnIndex("id"))
                namebuyer = cursor.getString(cursor.getColumnIndex("namebuyer"))
                ci = cursor.getString(cursor.getColumnIndex("ci"))
                quantity = cursor.getString(cursor.getColumnIndex("quantity"))
                totalp = cursor.getString(cursor.getColumnIndex("cantidadP"))
                metod = cursor.getString(cursor.getColumnIndex("metod"))
                date = cursor.getString(cursor.getColumnIndex("date"))


                val std = BuyerModel(
                    id = id,
                NAMEBUYER = namebuyer,
                CI = ci,
                QUANTITY = quantity,
                TOTALP =  totalp,
                METOD = metod,
                DATE = date

                )
                stdlist.add(std)

            } while (cursor.moveToNext())

        }

        return stdlist

    }

    fun updateBuyer(std: BuyerModel): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(NAMEBUYER, std.NAMEBUYER)
        contentValues.put(CI, std.CI)
        contentValues.put(QUANTITY, std.QUANTITY)
        contentValues.put(TOTALP, std.TOTALP)
        contentValues.put(METOD,std.METOD)
        contentValues.put(DATE,std.DATE)

        val success = db.update(TBL_BUYER, contentValues,"id=" + std.id, null)
        db.close()
        return success
    }

    fun deleteRegisterById(id: Int): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, id)

        val success = db.delete(TBL_BUYER, "id=$id", null)
        db.close()
        return success
    }
}