package com.example.buyregister

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BuyerAdapter: RecyclerView.Adapter<BuyerAdapter.BuyerViewHolder>() {


    private var stdList: ArrayList<BuyerModel> = ArrayList()
    private var setOnClickItem: ((BuyerModel) -> Unit)? = null
    private var setOnClickDeleteItem: ((BuyerModel)-> Unit)? = null

    fun addBuy(items: ArrayList<BuyerModel>){
        this.stdList = items
        notifyDataSetChanged()
    }

    fun setOnClickItem(callback: (BuyerModel) -> Unit){
        this.setOnClickItem = callback
    }

    fun setOnClickDeleteItem (callback: (BuyerModel) -> Unit ){
        this.setOnClickDeleteItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int) = BuyerViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_view_buy, parent, false)
    )

    override fun onBindViewHolder(holder: BuyerViewHolder, position: Int) {
        val std = stdList[position]
        holder.bindView(std)
        holder.itemView.setOnClickListener{setOnClickItem?.invoke(std)}
        holder.btnDeleteC.setOnClickListener{setOnClickDeleteItem?.invoke(std)}
    }

    override fun getItemCount(): Int {
        return stdList.size
    }

    class BuyerViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        private var namec = view.findViewById<TextView>(R.id.tvNameC)
        private var quantity = view.findViewById<TextView>(R.id.tvQuantity)
        private var date = view.findViewById<TextView>(R.id.tvDate)
        var btnDeleteC = view.findViewById<Button>(R.id.btnDeleteC)


        fun bindView(std: BuyerModel) {


            namec.text = std.NAMEBUYER
            quantity.text = std.QUANTITY
            date.text = std.DATE


        }

    }


}