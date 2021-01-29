package com.kotlinmvvm.taobao.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlinmvvm.R
import com.kotlinmvvm.taobao.adapter.OnSellListAdapter.InnerHolder
import com.kotlinmvvm.taobao.domain.OnSellData
import kotlinx.android.synthetic.main.item_on_sell.view.*

class OnSellListAdapter : RecyclerView.Adapter<InnerHolder>() {
    private var mContentList =
        arrayListOf<OnSellData.TbkDgOptimusMaterialResponse.ResultList.MapData>()

    inner class InnerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerHolder {
        return InnerHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_on_sell, parent, false)
        )
    }

    override fun onBindViewHolder(holder: InnerHolder, position: Int) {
        holder.itemView.apply {
            with(mContentList[position]) {
                tvTitle.text = title
                tvPrice.text = String.format("%.2f", zk_final_price.toFloat() - coupon_amount)
                Glide
                    .with(context)
                    .load("https:$pict_url")
                    .into(ivIcon)
            }
        }
    }

    override fun getItemCount(): Int {
        return mContentList.size
    }

    fun setData(mutableList: MutableList<OnSellData.TbkDgOptimusMaterialResponse.ResultList.MapData>) {
        mContentList.clear()
        mContentList.addAll(mutableList)
        notifyDataSetChanged()
    }
}