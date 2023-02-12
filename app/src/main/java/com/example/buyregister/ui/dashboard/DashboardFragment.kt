package com.example.buyregister.ui.dashboard

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buyregister.*
import com.example.buyregister.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private var adapter: BuyerAdapter? = null


    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var sqLiteHelper: SQLiteHelperC

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
                sqLiteHelper = SQLiteHelperC(requireContext())
        val button: Button = binding.btnAddBuy
        button.setOnClickListener() {
            val intent = Intent(activity, BuyerActivity::class.java)
            startActivity(intent)

        }

        recyclerView = binding.ListaCompradores

        initRecyclerView1()
        getBuy()

        adapter?.setOnClickItem {

            val intent = Intent(requireContext(),BuyerActivity::class.java)
            intent.putExtra("isBuy",true)
            intent.putExtra("id",it.id)
            intent.putExtra("nameB",it.NAMEBUYER)
            intent.putExtra("ci",it.CI)
            intent.putExtra("cantidadP",it.TOTALP)
            intent.putExtra("quantity", it.QUANTITY)
            intent.putExtra("metod", it.METOD)
            intent.putExtra("date", it.DATE)

            startActivity(intent)



        }

        adapter?.setOnClickDeleteItem {
            deleteRegister(it.id)

        }
        return root
    }

    private fun deleteRegister(id: Int){
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("estas seguro de eliminar el Registro")
        builder.setCancelable(true)
        builder.setPositiveButton("si"){ dialog, _ ->
            sqLiteHelper.deleteRegisterById(id)
            getBuy()
            dialog.dismiss()
        }
        builder.setNegativeButton("No"){ dialog, _ ->

            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }


    private fun getBuy() {
        val stdList = sqLiteHelper.getAllBuy()
        Log.e("pppp", "${stdList.size}")
        adapter?.addBuy(stdList)
    }

    override fun onResume() {
        super.onResume()
        getBuy()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView1() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = BuyerAdapter()
        recyclerView.adapter = adapter

    }
}