package com.doducnghia00.sleepsounds.fragment

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.doducnghia00.sleepsounds.R

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment(), View.OnClickListener, MediaPlayer.OnPreparedListener {
    private lateinit  var rain : ImageButton
    lateinit  var thunderstorm : ImageButton
    lateinit  var rainLeaves : ImageButton
    lateinit  var wind : ImageButton

    lateinit  var mediaPlayer1 : MediaPlayer
    lateinit  var mediaPlayer2 : MediaPlayer
    lateinit  var mediaPlayer3 : MediaPlayer
    lateinit  var mediaPlayer4 : MediaPlayer

    lateinit var seekVol : SeekBar

    lateinit var audioManager : AudioManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        addSounds()

        rain.setOnClickListener(this)
        thunderstorm.setOnClickListener(this)
        rainLeaves.setOnClickListener(this)
        wind.setOnClickListener(this)
    }

    companion object {

    }

    private fun addSounds() {
        mediaPlayer1 = MediaPlayer.create(context, R.raw.rain_main)
        mediaPlayer1.isLooping = true
        mediaPlayer2 = MediaPlayer.create(context, R.raw.thunderstorm)
        mediaPlayer2.isLooping = true
        mediaPlayer3 = MediaPlayer.create(context, R.raw.rain_on_leaves)
        mediaPlayer3.isLooping = true
        mediaPlayer4 = MediaPlayer.create(context, R.raw.wind)
        mediaPlayer4.isLooping = true

    }

    private fun initView(view: View) {
        rain = view.findViewById(R.id.rainy)
        thunderstorm = view.findViewById(R.id.thunderstorm)
        rainLeaves = view.findViewById(R.id.rainOnLeaves)
        wind = view.findViewById(R.id.wind)
    }

    override fun onClick(p0: View?) {
        when(p0){
            rain -> {
                if (!mediaPlayer1.isPlaying){
                    mediaPlayer1.start()
                    rain.setBackgroundResource(R.drawable.button_bg_selected)
                    Log.e("Test", "Type Resource ${R.raw.rain_main}")
                }else{
                    mediaPlayer1.stop()
                    mediaPlayer1.prepare()
                    rain.setBackgroundResource(R.drawable.button_bg)
                }
            }
            thunderstorm -> {
                if (!mediaPlayer2.isPlaying){
                    mediaPlayer2.start()
                    thunderstorm.setBackgroundResource(R.drawable.button_bg_selected)
                }else{
                    mediaPlayer2.stop()
                    mediaPlayer2.prepare()
                    thunderstorm.setBackgroundResource(R.drawable.button_bg)
                }
            }
            rainLeaves -> {
                if (!mediaPlayer3.isPlaying){
                    mediaPlayer3.start()
                    rainLeaves.setBackgroundResource(R.drawable.button_bg_selected)
                }else{
                    mediaPlayer3.stop()
                    mediaPlayer3.prepare()
                    rainLeaves.setBackgroundResource(R.drawable.button_bg)
                }
            }
            wind -> {
                if (!mediaPlayer4.isPlaying){
                    mediaPlayer4.start()
                    wind.setBackgroundResource(R.drawable.button_bg_selected)
                }else{
                    mediaPlayer4.stop()
                    mediaPlayer4.prepare()
                    wind.setBackgroundResource(R.drawable.button_bg)
                }
            }
        }
    }

    override fun onPrepared(p0: MediaPlayer?) {
        //TODO("Not yet implemented")
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer1.stop()
        mediaPlayer2.stop()
        mediaPlayer3.stop()
        mediaPlayer4.stop()
    }
}