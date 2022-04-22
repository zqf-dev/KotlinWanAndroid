package com.zqf.kotlinwanandroid.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.youth.banner.adapter.BannerAdapter
import com.zqf.kotlinwanandroid.R
import com.zqf.kotlinwanandroid.entity.BannerEntity
import com.zqf.kotlinwanandroid.util.glideLoad

/**
 * Author: zqf
 * Date: 2021/11/08
 */
class HomeBannerAdapter(data: List<BannerEntity>) :
    BannerAdapter<BannerEntity, HomeBannerAdapter.ImgViewHolder>(data) {

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): ImgViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.home_banner_iamge, parent, false)
        return ImgViewHolder(view)
    }

    override fun onBindView(holder: ImgViewHolder, data: BannerEntity, position: Int, size: Int) {
        glideLoad(data.imagePath, holder.image)
    }

    class ImgViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image: AppCompatImageView = view.findViewById(R.id.home_banner_image)
    }
}