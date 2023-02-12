package com.example.buyregister.ui.home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buyregister.*
import com.example.buyregister.APIHelper.DolarAPI
import com.example.buyregister.APIHelper.USD
import com.example.buyregister.APIHelper.respuestaDolar
import com.example.buyregister.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment() {

    private lateinit var TvDolars: TextView
    private lateinit var TvDolar: TextView
    private lateinit var recyclerView: RecyclerView
    private var adapter: ProductAdapter? = null


private var _binding: FragmentHomeBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!



    private lateinit var sqLiteHelper: SQLiteHelper


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val root: View = binding.root

      TvDolar = binding.TvDolar
      TvDolars = binding.TvDolars

      sqLiteHelper = SQLiteHelper(requireContext())
    val button: Button = binding.btnAdd
      button.setOnClickListener() {
          val intent = Intent(activity, productoActivity::class.java)
          startActivity(intent)

      }


      recyclerView = binding.ListaProducto


      initRecyclerView()
        getProduct()
         callDolar()

      adapter?.setOnClickItem {



          val intent : Intent = Intent(requireContext(),productoActivity::class.java)
          intent.putExtra("isProduct",true)
          intent.putExtra("id",it.id)
          intent.putExtra("name",it.product)
          intent.putExtra("dt",it.distributor)
          intent.putExtra("price",it.prize)


          startActivity(intent)

      }

      adapter?.setOnClickDeleteItem {
        deleteItem(it.id)

      }
      return root
  }

    private fun deleteItem(id: Int){
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("estas seguro de eliminar el producto")
        builder.setCancelable(true)
        builder.setPositiveButton("si"){ dialog, _ ->
            sqLiteHelper.deleteProductById(id)
            getProduct()
            dialog.dismiss()
        }
        builder.setNegativeButton("No"){ dialog, _ ->

            dialog.dismiss()
        }
            val alert = builder.create()
            alert.show()
    }

    private fun getProduct() {
        val stdList = sqLiteHelper.getAllProducts()
        Log.e("pppp", "${stdList.size}")
        adapter?.addProduct(stdList)
    }

    override fun onResume() {
        super.onResume()
        getProduct()
    }

override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = ProductAdapter()
        recyclerView.adapter = adapter
    }



    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://s3.amazonaws.com/dolartoday/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun callDolar() {

        val DolarApiService: DolarAPI = getRetrofit().create(DolarAPI::class.java)
        DolarApiService.getDolar().enqueue(object: Callback<respuestaDolar> {
            override fun onResponse(
                call: Call<respuestaDolar>,
                response: Response<respuestaDolar>
            ) {
                if (response.isSuccessful) {
                    val dolita: respuestaDolar = response.body() as respuestaDolar

                    TvDolars.text = "DOLAR BCV: "+dolita.USD?.sicad2.toString()
                    TvDolar.text = "DOLAR PARALELO: "+dolita.USD?.dolartoday.toString()
                    Toast.makeText(requireContext(),dolita.USD?.efectivo.toString(), Toast.LENGTH_SHORT).show()

                } else {
                        TvDolar.text = "Ha ocurrido un error"
                    Log.d("Error", "Something happened")
                    return
                }
            }

            override fun onFailure(call: Call<respuestaDolar>, t: Throwable) {
                Log.d("Error", t.toString())
            }


        })

    }




}