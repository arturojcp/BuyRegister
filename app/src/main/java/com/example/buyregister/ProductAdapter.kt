package com.example.buyregister

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>()  {

    private var stdList: ArrayList<ProductModel> = ArrayList()
    private var setOnClickItem: ((ProductModel) -> Unit)? = null
private var setOnClickDeleteItem: ((ProductModel)-> Unit)? = null

    fun addProduct(items: ArrayList<ProductModel>){
        this.stdList = items
        notifyDataSetChanged()
    }

    fun setOnClickItem(callback: (ProductModel) -> Unit){
        this.setOnClickItem = callback
    }

    fun setOnClickDeleteItem (callback: (ProductModel) -> Unit ){
        this.setOnClickDeleteItem = callback
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int) = ProductViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_view_std, parent, false)
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val std = stdList[position]
        holder.bindView(std)
        holder.itemView.setOnClickListener{setOnClickItem?.invoke(std)}
        holder.btnDelete.setOnClickListener{setOnClickDeleteItem?.invoke(std)}
    }

    override fun getItemCount(): Int {
        return stdList.size
    }

    class ProductViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private var id = view.findViewById<TextView>(R.id.tvId)
        private var name = view.findViewById<TextView>(R.id.tvName)
        private var distributor = view.findViewById<TextView>(R.id.tvDistributor)
        private var prize = view.findViewById<TextView>(R.id.tvPrize)
        var btnDelete = view.findViewById<Button>(R.id.btnDelete)


        fun bindView(std: ProductModel) {

            id.text = std.id.toString()
            name.text = std.product
            distributor.text = std.distributor
            prize.text = std.prize


        }

    }

}