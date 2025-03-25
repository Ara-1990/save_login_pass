package com.the.saveloginpass


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*


class Adapter(
   var imageList: Array<Int>,
   var context: Context,
   var aray_list: ArrayList<String>,
   var rv_activity: RecyclerActivity,
) : RecyclerView.Adapter<Adapter.ViewHolder>() {





    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var text_View = view.findViewById<TextView>(R.id.tv)
        var crcle_imageView = view.findViewById<CircleImageView>(R.id.circle_iv)
        var card_View = view.findViewById<CardView>(R.id.card_v)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.text_View.text = aray_list[position]
        Glide.with(context).load(imageList[position]).into(holder.crcle_imageView)

        holder.card_View.setOnClickListener { rv_activity.activity_change(holder.adapterPosition) }

    }

    override fun getItemCount(): Int {

        return aray_list.size
    }


}