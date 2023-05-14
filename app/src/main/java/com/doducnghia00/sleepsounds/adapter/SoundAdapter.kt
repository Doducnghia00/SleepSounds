package com.doducnghia00.sleepsounds.adapter

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.doducnghia00.sleepsounds.R
import com.doducnghia00.sleepsounds.model.Sound

class SoundAdapter(private val mContext: Context) :
    RecyclerView.Adapter<SoundAdapter.SoundViewHolder>() {
    private lateinit var mList: List<Sound>

    private lateinit var runningList : BooleanArray
    private lateinit var mListener : onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }
    fun setData(list: List<Sound>, running: BooleanArray) {
        mList = list
        runningList = running
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

        if(runningList[position]){
            holder.img.setBackgroundResource(R.drawable.button_bg_selected)
        }else{
            holder.img.setBackgroundResource(R.drawable.button_bg)
        }
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


            //init media player

            itemView.setOnClickListener{

                listener.onItemClick(layoutPosition)


                val position = layoutPosition

                Log.e("Test","Clicked position $position")

                //If sound is running
//                if (runningList[position]){
//                    runningList[position] = false
//                    img.setBackgroundResource(R.drawable.button_bg)
//                }else{ // If sound isn't running
//
//                    runningList[position] = true
//
//                    //Change background color
//                    img.setBackgroundResource(R.drawable.button_bg_selected)
//                }
            }
        }
    }
}