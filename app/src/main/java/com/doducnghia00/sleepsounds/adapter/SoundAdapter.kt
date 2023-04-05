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
    //Move media player from fragment to adapter
    private val mPlayer  = arrayOf(-1, -1, -1, -1)
    private lateinit var runningList : BooleanArray
    private lateinit var mediaPlayer : MediaPlayer
    private lateinit var mediaPlayer1 : MediaPlayer
    private lateinit var mediaPlayer2 : MediaPlayer
    private lateinit var mediaPlayer3 : MediaPlayer

    private lateinit var mListener : onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }
    fun setData(list: List<Sound>) {
        mList = list
        runningList = BooleanArray(list.size)
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


            //init media player
            initMedia()

            itemView.setOnClickListener{
                //listener.onItemClick(layoutPosition)

                //Move media player from fragment to adapter
                val position = layoutPosition

                Log.e("Test","Clicked position $position")

                //If sound is running
                if (runningList[position]){
                    Log.e("Test", "Sound is running -> Try stop sound")
                    runningList[position] = false
                    if (stopSound(position)) Log.e("Test", "Stopper sound")
                    //Change background color play
                    img.setBackgroundResource(R.drawable.button_bg)
                }else{ // If sound isn't running
                    Log.e("Test", "Sound isn't running -> Try play sound")

                    if (!playSound(position)){
                        Log.e("Test","Error! Media Player Full!")
                        Toast.makeText(mContext,"Error! Media Player Full!", Toast.LENGTH_SHORT).show()
                    }else{
                        runningList[position] = true
                        Log.e("Test","Played sound")

                        //Change background color
                        img.setBackgroundResource(R.drawable.button_bg_selected)
                    }
                }
                Log.e("Test", "Media Player: ${mPlayer[0]} ${mPlayer[1]} ${mPlayer[2]} ${mPlayer[3]}")
                Log.e("Test", "==================")
            }
        }


    }

    private fun initMedia() {
        mediaPlayer = MediaPlayer()
        mediaPlayer1 = MediaPlayer()
        mediaPlayer2 = MediaPlayer()
        mediaPlayer3 = MediaPlayer()
    }

    // Try play sound
    private fun playSound(position: Int) :Boolean {
        Log.e("Test", "Play Sound Function")
        //Select media-player
        for (i in mPlayer.indices){
            if (mPlayer[i] == -1){
                Log.e("Test", "Media player number $i")
                when(i){
                    0->{
                        mediaPlayer = MediaPlayer.create(mContext, mList[position].raw)
                        mediaPlayer.isLooping = true
                        mediaPlayer.start()
                    }
                    1->{
                        mediaPlayer1 = MediaPlayer.create(mContext, mList[position].raw)
                        mediaPlayer1.isLooping = true
                        mediaPlayer1.start()
                    }
                    2->{
                        mediaPlayer2 = MediaPlayer.create(mContext, mList[position].raw)
                        mediaPlayer2.isLooping = true
                        mediaPlayer2.start()
                    }
                    3->{
                        mediaPlayer3 = MediaPlayer.create(mContext, mList[position].raw)
                        mediaPlayer3.isLooping = true
                        mediaPlayer3.start()
                    }
                }
                mPlayer[i] = position
                return true
            }
        }
        return false
    }

    //Try stop sound
    private fun stopSound(position: Int): Boolean {
        Log.e("Test", "Stop Sound Function")
        for (i in mPlayer.indices){
            if (mPlayer[i] == position){
                Log.e("Test", "Media Player number $i")
                when(i){
                    0 -> mediaPlayer.stop()
                    1 -> mediaPlayer1.stop()
                    2 -> mediaPlayer2.stop()
                    3 -> mediaPlayer3.stop()
                }
                mPlayer[i] = -1
                return true
            }
        }
        return false
    }

    fun showStatusMP(){
        Log.e("Test", "on Resume Media Player: ${mPlayer[0]} ${mPlayer[1]} ${mPlayer[2]} ${mPlayer[3]}")
        Log.e("Test", "==================")
    }

    fun stopAllMP(){
        val l = listOf(mediaPlayer, mediaPlayer1, mediaPlayer2, mediaPlayer3)
        for (i in l.indices){
            if (l[i].isPlaying) l[i].stop()
        }
    }


}