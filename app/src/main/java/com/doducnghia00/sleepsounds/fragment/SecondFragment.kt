package com.doducnghia00.sleepsounds.fragment

import android.content.Context
import android.content.Context.AUDIO_SERVICE
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doducnghia00.sleepsounds.MainActivity
import com.doducnghia00.sleepsounds.R
import com.doducnghia00.sleepsounds.adapter.SoundAdapter
import com.doducnghia00.sleepsounds.model.Sound


class SecondFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

    private var recyclerView : RecyclerView?= null
    private lateinit var soundAdapter : SoundAdapter
    private lateinit var list: List<Sound>
    private val mPlayer  = arrayOf(-1, -1, -1, -1)
    private lateinit var runningList : BooleanArray
    private lateinit var mediaPlayer : MediaPlayer
    private lateinit var mediaPlayer1 : MediaPlayer
    private lateinit var mediaPlayer2 : MediaPlayer
    private lateinit var mediaPlayer3 : MediaPlayer

    private lateinit var seekVol : SeekBar
    private lateinit var audioManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMedia()

        soundAdapter = SoundAdapter(requireContext())


        list = getListSound()
        runningList = BooleanArray(list.size)


        soundAdapter.setData(list)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView?.adapter = soundAdapter

        val gridLayoutManager = GridLayoutManager(context,4)
        recyclerView?.layoutManager = gridLayoutManager

        soundAdapter.setOnItemClickListener(object : SoundAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(context, "Click ${list[position].name}", Toast.LENGTH_LONG).show()
                Log.e("Test","Clicked position $position")

                //If sound is running
                if (runningList[position]){
                    Log.e("Test", "Sound is running -> Try stop sound")
                    runningList[position] = false
                    if (stopSound(position)) Log.e("Test", "Stopper sound")
                }else{ // If sound isn't running
                    Log.e("Test", "Sound isn't running -> Try play sound")

                    if (!playSound(position)){
                        Log.e("Test","Error! Media Player Full!")
                    }else{
                        runningList[position] = true
                        Log.e("Test","Played sound")
                    }
                }
                Log.e("Test", "Media Player: ${mPlayer[0]} ${mPlayer[1]} ${mPlayer[2]} ${mPlayer[3]}")
                Log.e("Test", "==================")
            }

        })


        // Control volume with seekBar
        seekVol = view.findViewById(R.id.seekVol)
        audioManager = context?.getSystemService(AUDIO_SERVICE) as AudioManager

        val maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        seekVol.max = maxVol

        val curVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        seekVol.progress = curVol

        seekVol.setOnSeekBarChangeListener(this)
    }

    private fun initMedia() {
        mediaPlayer = MediaPlayer.create(context,R.raw.rain_main)
        mediaPlayer1 = MediaPlayer.create(context,R.raw.rain_main)
        mediaPlayer2 = MediaPlayer.create(context,R.raw.rain_main)
        mediaPlayer3 = MediaPlayer.create(context,R.raw.rain_main)
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
                        mediaPlayer = MediaPlayer.create(context, list[position].raw)
                        mediaPlayer.isLooping = true
                        mediaPlayer.start()
                    }
                    1->{
                        mediaPlayer1 = MediaPlayer.create(context, list[position].raw)
                        mediaPlayer1.isLooping = true
                        mediaPlayer1.start()
                    }
                    2->{
                        mediaPlayer2 = MediaPlayer.create(context, list[position].raw)
                        mediaPlayer2.isLooping = true
                        mediaPlayer2.start()
                    }
                    3->{
                        mediaPlayer3 = MediaPlayer.create(context, list[position].raw)
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

    private fun getListSound(): List<Sound> {
        val mList: MutableList<Sound> = ArrayList()
        mList.add(Sound(1, "Rain", R.drawable.rainy, R.raw.rain_main))
        mList.add(Sound(2, "Thunderstorm", R.drawable.thunderstorm, R.raw.thunderstorm))
        mList.add(Sound(3, "Rain on leaves", R.drawable.leaf, R.raw.rain_on_leaves))
        mList.add(Sound(4, "Wind", R.drawable.air, R.raw.wind))

        mList.add(Sound(5, "Rain", R.drawable.rainy, R.raw.rain_main))
        mList.add(Sound(6, "Thunderstorm", R.drawable.thunderstorm, R.raw.thunderstorm))
        mList.add(Sound(7, "Rain on leaves", R.drawable.leaf, R.raw.rain_on_leaves))
        mList.add(Sound(8, "Wind", R.drawable.air, R.raw.wind))

        return mList
    }
    //TODO Solve the problem of data loss when onResume
    override fun onResume() {
        super.onResume()
        soundAdapter.showStatusMP()
    }

    override fun onPause() {
        super.onPause()
        soundAdapter.stopAllMP()
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        if (p0 == seekVol){
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, p1, 0)
        }
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {

    }


}