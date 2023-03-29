package com.doducnghia00.sleepsounds

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build.VERSION_CODES.O
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener, MediaPlayer.OnPreparedListener{

    lateinit  var rain :ImageButton
    lateinit  var thunderstorm :ImageButton
    lateinit  var rainLeaves :ImageButton
    lateinit  var wind :ImageButton

    lateinit  var mediaPlayer1 : MediaPlayer
    lateinit  var mediaPlayer2 : MediaPlayer
    lateinit  var mediaPlayer3 : MediaPlayer
    lateinit  var mediaPlayer4 : MediaPlayer

    lateinit var seekVol : SeekBar

    lateinit var audioManager : AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        addSounds()

        rain.setOnClickListener(this)
        thunderstorm.setOnClickListener(this)
        rainLeaves.setOnClickListener(this)
        wind.setOnClickListener(this)


    }

    private fun addSounds() {
        mediaPlayer1 = MediaPlayer.create(this, R.raw.rain_main)
        mediaPlayer1.isLooping = true
        mediaPlayer2 = MediaPlayer.create(this, R.raw.thunderstorm)
        mediaPlayer2.isLooping = true
        mediaPlayer3 = MediaPlayer.create(this, R.raw.rain_on_leaves)
        mediaPlayer3.isLooping = true
        mediaPlayer4 = MediaPlayer.create(this, R.raw.wind)
        mediaPlayer4.isLooping = true
    }

    private fun initView() {
        rain = findViewById(R.id.rainy)
        thunderstorm = findViewById(R.id.thunderstorm)
        rainLeaves = findViewById(R.id.rainOnLeaves)
        wind = findViewById(R.id.wind)
    }

    override fun onClick(p0: View?) {
        when(p0){
            rain -> {
                if (!mediaPlayer1.isPlaying){
                    mediaPlayer1.start()
                    rain.setBackgroundResource(R.drawable.button_bg_selected)
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


}

