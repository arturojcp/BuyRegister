package com.example.buyregister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class productoActivity : AppCompatActivity() {
    private lateinit var edName: EditText
    private lateinit var edDistributor: EditText
    private lateinit var edPrize: EditText
    private lateinit var btnSave: Button
    private lateinit var btnUpdate: Button
    private lateinit var btnView: Button


    private lateinit var sqLiteHelper: SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: ProductAdapter? = null
    private var std:ProductModel? = null
    private var IDProduct : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producto)

        newActivity()
        sqLiteHelper = SQLiteHelper(this)

        btnSave.setOnClickListener{ addProduct()}
        btnUpdate.setOnClickListener{ getProduct()}




        if(intent.extras?.getBoolean("isProduct") == true){
            std = ProductModel(id= intent.extras!!.getInt("id"),product = intent.extras?.getString("name").toString(), distributor = intent.extras?.getString("dt").toString(), prize = intent.extras?.getString("price").toString())
            IDProduct = intent.extras!!.getInt("id")
            edName.setText(intent.extras?.getString("name").toString())
            edDistributor.setText(intent.extras?.getString("dt").toString())
            edPrize.setText(intent.extras?.getString("price").toString())



        }
    }

    private fun getProduct() {
        val stdList = sqLiteHelper.getAllProducts()

    }

    private fun addProduct(){

    val product = edName.text.toString()
    val distributor = edDistributor.text.toString()
    val prize = edPrize.text.toString()



    if(product.isEmpty() || distributor.isEmpty() || prize.isEmpty()) {

        Toast.makeText(this, "por favor rellene los espacios vacios", Toast.LENGTH_SHORT).show()
    } else {
        //SI HAY SETEADO UN ID
         val stdlocal = ProductModel(product = product, distributor = distributor, prize = prize)

        if(IDProduct != 0)
        {

            //editproduct

            updateProduct()
        }else
        {
            val status = sqLiteHelper.insertProduct(stdlocal)

            if (status > -1) {
                Toast.makeText(this, "producto agregado", Toast.LENGTH_SHORT).show()

                clearEditText()
                finish()
            } else {
                Toast.makeText(this, "no guardado", Toast.LENGTH_SHORT).show()

            }
        }
        }


    }

    private fun updateProduct() {
        val product = edName.text.toString()
        val distributor = edDistributor.text.toString()
        val prize = edPrize.text.toString()

if (product == std?.product && distributor == std?.distributor && prize == std?.prize){

    Toast.makeText(this, "No hay cambios", Toast.LENGTH_SHORT).show()
    return
}
        if (std == null ) return

        val stdLocal = ProductModel(id = IDProduct, product = product, distributor = distributor, prize = prize)
        val status = sqLiteHelper.updateProduct(stdLocal)
        if(status > -1){
            Toast.makeText(this, "producto editado", Toast.LENGTH_SHORT).show()
            clearEditText()
            getProduct()
            finish()
        }else{
            Toast.makeText(this, "actualizacion fallida", Toast.LENGTH_SHORT).show()
        }

    }

    private fun clearEditText(){
        edName.setText("")
        edPrize.setText("")
        edDistributor.setText("")
        edName.requestFocus()
    }



    private fun newActivity(){
        edName = findViewById(R.id.edName)
        edDistributor = findViewById(R.id.edDistributor)
        edPrize = findViewById(R.id.edPrize)
        btnSave = findViewById(R.id.btnSave)
        btnUpdate = findViewById(R.id.btnUpdate)
    }


}



