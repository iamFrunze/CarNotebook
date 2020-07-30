package com.byfrunze.carnotebook.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.byfrunze.carnotebook.R
import com.byfrunze.carnotebook.models.Brand
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cell_choose_brand.view.*

class BrandAdapter : RecyclerView.Adapter<BrandAdapter.ViewHolder>() {

    private val arrayOfBrands = ArrayList<Brand>()

    fun setupArrayOfBrands(arrayList: ArrayList<Brand>) {
        arrayOfBrands.addAll(arrayList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cell_choose_brand, parent, false)
        )

    override fun getItemCount() = arrayOfBrands.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arrayOfBrands[position])
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val logoIV = item.findViewById<ImageView>(R.id.cell_logo_iv)
        private val brandTXT = item.findViewById<TextView>(R.id.cell_brand_txt)

        fun bind(model: Brand) {
            Picasso.get()
                .load(model.logo)
                .resize(48, 48)
                .into(logoIV)
            brandTXT.text = model.name
        }
    }
}