package com.doducnghia00.sleepsounds

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build.VERSION_CODES.O
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit  var play : ImageButton
    lateinit  var pause : ImageButton
    lateinit  var stop : ImageButton
    lateinit  var next : ImageButton
    lateinit  var previous : ImageButton
    lateinit  var mediaPlayer : MediaPlayer

    lateinit var seekTime : SeekBar
    lateinit var seekVol : SeekBar

    lateinit var audioManager : AudioManager
    lateinit var runnable : Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initializing views
        initView()

        //create an ArrayList songs


        mediaPlayer = MediaPlayer.create(this, R.raw.thunderstorm)

        play.setOnClickListener(this)
        pause.setOnClickListener(this)
        stop.setOnClickListener(this)
        next.setOnClickListener(this)
        previous.setOnClickListener(this)

    }

    private fun initView() {
        play = findViewById(R.id.play)
        pause = findViewById(R.id.pause)
        stop  = findViewById(R.id.stop)
        next = findViewById(R.id.next)
        previous = findViewById(R.id.previous)

        seekTime = findViewById(R.id.seekBarTime)
        seekVol = findViewById(R.id.seekBarVol)
    }

    override fun onClick(p0: View?) {
        when(p0){
            play ->{
                if(!mediaPlayer.isPlaying){
                    mediaPlayer.start()
                }
            }
            pause ->{
                if(mediaPlayer.isPlaying){
                    mediaPlayer.pause()
                }
            }
            stop->{
                if(mediaPlayer.isPlaying){
                    mediaPlayer.stop()
                    mediaPlayer.prepare()
                }
            }
            next->{

            }
            previous->{

            }
        }
    }


}

