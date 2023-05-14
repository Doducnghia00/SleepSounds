package com.doducnghia00.sleepsounds


import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.doducnghia00.sleepsounds.fragment.SecondFragment
import com.doducnghia00.sleepsounds.model.Sound
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(), SecondFragment.MyFragmentListener{

    var  soundPool: SoundPool = SoundPool.Builder().setMaxStreams(10).build()

    private lateinit var list: List<Sound>

    //Move media player from adapter to activity
    private val mPlayer  = arrayOf(-1, -1, -1, -1)
    private lateinit var runningList : BooleanArray
    private lateinit var mediaPlayer : MediaPlayer
    private lateinit var mediaPlayer1 : MediaPlayer
    private lateinit var mediaPlayer2 : MediaPlayer
    private lateinit var mediaPlayer3 : MediaPlayer



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav)
        val navController = findNavController(R.id.fragment)

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.firstFragment, R.id.secondFragment, R.id.thirdFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

        bottomNavigationView.setupWithNavController(navController)

        list = getListSound()
        initMedia()
        runningList = BooleanArray(list.size)


    }

    override fun onPause() {
        super.onPause()

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
                        mediaPlayer = MediaPlayer.create(this, list[position].raw)
                        mediaPlayer.seekTo(10000)
                        mediaPlayer.isLooping = true
                        mediaPlayer.start()
                    }
                    1->{
                        mediaPlayer1 = MediaPlayer.create(this, list[position].raw)
                        mediaPlayer1.isLooping = true
                        mediaPlayer1.start()
                    }
                    2->{
                        mediaPlayer2 = MediaPlayer.create(this, list[position].raw)
                        mediaPlayer2.isLooping = true
                        mediaPlayer2.start()
                    }
                    3->{
                        mediaPlayer3 = MediaPlayer.create(this, list[position].raw)
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

    override fun onSoundClick(position: Int) {
        Log.e("Test","Activity get $position")

        //If sound is running
        if (runningList[position]){
            Log.e("Test", "Sound is running -> Try stop sound")
            runningList[position] = false
            if (stopSound(position)) Log.e("Test", "Stopper sound")
            //Change background color play
            //img.setBackgroundResource(R.drawable.button_bg)
        }else{ // If sound isn't running
            Log.e("Test", "Sound isn't running -> Try play sound")

            if (!playSound(position)){
                Log.e("Test","Error! Media Player Full!")
                Toast.makeText(this,"Error! Media Player Full!", Toast.LENGTH_SHORT).show()
            }else{
                runningList[position] = true
                Log.e("Test","Played sound")

                //Change background color
                //img.setBackgroundResource(R.drawable.button_bg_selected)
            }
        }
        Log.e("Test", "Media Player: ${mPlayer[0]} ${mPlayer[1]} ${mPlayer[2]} ${mPlayer[3]}")
        Log.e("Test", "==================")

    }

    override fun getRunningList(): BooleanArray {
        return runningList
    }

    private fun getListSound(): List<Sound> {
        val mList: MutableList<Sound> = ArrayList()
        mList.add(Sound(0, "Rain", R.drawable.rainy, R.raw.rain_main))
        mList.add(Sound(1, "Thunderstorm", R.drawable.thunderstorm, R.raw.thunderstorm))
        mList.add(Sound(2, "Rain on leaves", R.drawable.leaf, R.raw.rain_on_leaves))
        mList.add(Sound(3, "Wind", R.drawable.air, R.raw.wind))

        mList.add(Sound(4, "Rain", R.drawable.rainy, R.raw.rain_main))
        mList.add(Sound(5, "Thunderstorm", R.drawable.thunderstorm, R.raw.thunderstorm))
        mList.add(Sound(6, "Rain on leaves", R.drawable.leaf, R.raw.rain_on_leaves))
        mList.add(Sound(7, "Wind", R.drawable.air, R.raw.wind))

        return mList
    }

    private fun initMedia() {
        mediaPlayer = MediaPlayer()
        mediaPlayer1 = MediaPlayer()
        mediaPlayer2 = MediaPlayer()
        mediaPlayer3 = MediaPlayer()


    }


}

