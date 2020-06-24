package com.example.appwidget

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class GalleryAdapter(context: Context, private val sList: ArrayList<Int>) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return sList.size
    }

    override fun getItem(position: Int): Any {
        return sList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        @SuppressLint("viewHolder")
        val cView: View = inflater.inflate(R.layout.flip_item, parent, false)
        val imageView = cView.findViewById<ImageView>(R.id.img)
        imageView?.setImageResource(sList[position])
        return cView
    }
}