package com.example.buyregister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class BuyerActivity : AppCompatActivity() {
    private lateinit var edNameBuyer: EditText
    private lateinit var edCI: EditText
    private lateinit var edQuantity: EditText
    private lateinit var edTotalP: EditText
    private lateinit var edMetod: EditText
    private lateinit var edDate: EditText
    private lateinit var btnSaveR: Button
    private var IDBuy: Int = 0

    private lateinit var SQLiteHelperC: SQLiteHelperC
    private var std:BuyerModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyer)

        newActivity()
        SQLiteHelperC = SQLiteHelperC(this)
        btnSaveR.setOnClickListener{ addBuy() }

        if(intent.extras?.getBoolean("isBuy") == true){
            std = BuyerModel(id= intent.extras!!.getInt("id"), NAMEBUYER = intent.extras?.getString("nameB").toString(), CI = intent.extras?.getString("ci").toString(), TOTALP = intent.extras?.getString("cantidadP").toString(), QUANTITY = intent.extras?.getString("quantity").toString(), METOD = intent.extras?.getString("metod").toString(), DATE = intent.extras?.getString("date").toString())
            IDBuy = intent.extras!!.getInt("id")
            edNameBuyer.setText(intent.extras?.getString("nameB").toString())
            edCI.setText(intent.extras?.getString("ci").toString())
            edTotalP.setText(intent.extras?.getString("cantidadP").toString())
            edQuantity.setText(intent.extras?.getString("quantity").toString())
            edMetod.setText(intent.extras?.getString("metod").toString())
            edDate.setText(intent.extras?.getString("date").toString())


        }


    }

    private fun getBuy() {
        val stdList = SQLiteHelperC.getAllBuy()
    }

    private fun addBuy(){

        val namebuyer = edNameBuyer.text.toString()
        val ci = edCI.text.toString()
        val quantity = edQuantity.text.toString()
        val totalp =  edTotalP.text.toString()
        val metod = edMetod.text.toString()
        val date = edDate.text.toString()


        if(namebuyer.isEmpty() || ci.isEmpty() || quantity.isEmpty() || totalp.isEmpty() || metod.isEmpty() || date.isEmpty()) {

            Toast.makeText(this, "por favor rellene los espacios vacios", Toast.LENGTH_SHORT).show()
        } else {
            //SI HAY SETEADO UN ID
            val stdlocal = BuyerModel(NAMEBUYER = namebuyer, CI = ci, QUANTITY = quantity, TOTALP = totalp, METOD = metod, DATE = date)

            if(IDBuy != 0)
            {

                //editproduct

                updateBuyer()
            }else
            {
                val status = SQLiteHelperC.insertBuy(stdlocal)

                if (status > -1) {
                    Toast.makeText(this, "venta registrada", Toast.LENGTH_SHORT).show()

                    clearEditText()
                    finish()
                } else {
                    Toast.makeText(this, "no se registro la venta", Toast.LENGTH_SHORT).show()

                }
            }
        }


    }

    private fun updateBuyer() {
        val namebuyer = edNameBuyer.text.toString()
        val ci = edCI.text.toString()
        val quantity = edQuantity.text.toString()
        val totalp =  edTotalP.text.toString()
        val metod = edMetod.text.toString()
        val date = edDate.text.toString()

        if (namebuyer == std?.NAMEBUYER && ci == std?.CI && quantity == std?.QUANTITY && totalp == std?.TOTALP && metod == std?.METOD && date == std?.DATE){

            Toast.makeText(this, "No hay cambios", Toast.LENGTH_SHORT).show()
            return
        }
        if (std == null ) return

        val stdLocal = BuyerModel(id = IDBuy, NAMEBUYER = namebuyer, CI = ci, QUANTITY = quantity, TOTALP = totalp, METOD = metod, DATE = date)
        val status = SQLiteHelperC.updateBuyer(stdLocal)
        if(status > -1){

            Toast.makeText(this, "Venta editada", Toast.LENGTH_SHORT).show()
            clearEditText()
            getBuy()
            finish()
        }else{
            Toast.makeText(this, "actualizacion fallida", Toast.LENGTH_SHORT).show()
        }

    }

    private fun clearEditText(){
        edNameBuyer.setText("")
        edCI.setText("")
        edQuantity.setText("")
        edTotalP.setText("")
        edMetod.setText("")
        edDate.setText("")
        edNameBuyer.requestFocus()
    }



    private fun newActivity(){
        edNameBuyer = findViewById(R.id.edNameBuyer)
        edCI = findViewById(R.id.edCI)
        edQuantity = findViewById(R.id.edQuantity)
        edTotalP = findViewById(R.id.edtotalP)
        edMetod = findViewById(R.id.edMetod)
        edDate = findViewById(R.id.edDate)
        btnSaveR = findViewById(R.id.btnSaveR)


    }
}