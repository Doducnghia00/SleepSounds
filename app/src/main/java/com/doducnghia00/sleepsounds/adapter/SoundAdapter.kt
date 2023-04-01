package com.doducnghia00.sleepsounds.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.doducnghia00.sleepsounds.R
import com.doducnghia00.sleepsounds.model.Sound

class SoundAdapter(private val mContext: Context) :
    RecyclerView.Adapter<SoundAdapter.SoundViewHolder>() {
    private var mList: List<Sound>? = null

    private lateinit var mListener : onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }
    fun setData(list: List<Sound>?) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sound, parent, false)
        return SoundViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: SoundViewHolder, position: Int) {
        val sound = mList!![position] ?: return
        holder.img.setImageResource(sound.img)
        holder.name.text = sound.name
    }

    override fun getItemCount(): Int {
        return if (mList != null) {
            mList!!.size
        } else 0
    }

    inner class SoundViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.iconSound)
        val name: TextView = itemView.findViewById(R.id.nameSound)

        init {

            itemView.setOnClickListener{
                listener.onItemClick(layoutPosition)
            }
        }


    }
}