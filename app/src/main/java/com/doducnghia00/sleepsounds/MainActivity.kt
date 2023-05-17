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
import com.doducnghia00.sleepsounds.databinding.ActivityMainBinding
import com.doducnghia00.sleepsounds.fragment.SecondFragment
import com.doducnghia00.sleepsounds.model.Sound
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity(), SecondFragment.MyFragmentListener{
    private lateinit var binding: ActivityMainBinding

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
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav)
//        val navController = findNavController(R.id.fragment)
        val bottomNavigationView = binding.nav
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
    private suspend fun playSound(position: Int) :Boolean {
        Log.e("Test", "Play Sound Function")
        return withContext(Dispatchers.Default){
            for (i in mPlayer.indices){
                if (mPlayer[i] == -1){
                    Log.e("Test", "Media player number $i")
                    when(i){
                        0->{
                            mediaPlayer = MediaPlayer.create(applicationContext, list[position].raw)
                            mediaPlayer.seekTo(10000)
                            mediaPlayer.isLooping = true
                            mediaPlayer.start()
                        }
                        1->{
                            mediaPlayer1 = MediaPlayer.create(applicationContext, list[position].raw)
                            mediaPlayer1.isLooping = true
                            mediaPlayer1.start()
                        }
                        2->{
                            mediaPlayer2 = MediaPlayer.create(applicationContext, list[position].raw)
                            mediaPlayer2.isLooping = true
                            mediaPlayer2.start()
                        }
                        3->{
                            mediaPlayer3 = MediaPlayer.create(applicationContext, list[position].raw)
                            mediaPlayer3.isLooping = true
                            mediaPlayer3.start()
                        }
                    }
                    mPlayer[i] = position
                    return@withContext true
                }
            }
            return@withContext false
        }
    }

    //Try stop sound
    private suspend fun stopSound(position: Int): Boolean {
        return withContext(Dispatchers.Default) {
            for (i in mPlayer.indices) {
                if (mPlayer[i] == position) {
                    Log.e("Test", "Media Player number $i")
                    when(i){
                        0 -> mediaPlayer.stop()
                        1 -> mediaPlayer1.stop()
                        2 -> mediaPlayer2.stop()
                        3 -> mediaPlayer3.stop()
                    }
                    mPlayer[i] = -1
                    return@withContext true
                }
            }
            return@withContext false
        }
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

    //TODO runningList wrong position, isSuccess not update
    override fun onSoundClick(position: Int) {
        Log.e("Test","Activity get $position")

        //If sound is running
        if (runningList[position]){
            Log.e("Test", "Sound is running -> Try stop sound")
            runningList[position] = false
            var isSuccess = false
            GlobalScope.async(Dispatchers.Main) {
                if (stopSound(position)){
                    Log.e("Test", "Stopper sound")
                    isSuccess = true
                }
            }
            Log.e("Test","isSuccess Stop = $isSuccess")

        }else{ // If sound isn't running
            Log.e("Test", "Sound isn't running -> Try play sound")
            runningList[position] = true
            var isSuccess = false
            GlobalScope.launch(Dispatchers.Main) {
                if (!playSound(position)){
                    Log.e("Test","Error! Media Player Full!")
                    Toast.makeText(applicationContext,"Error! Media Player Full!", Toast.LENGTH_SHORT).show()
                }else{
                    isSuccess = true
                    Log.e("Test","Played sound")
                }
            }
            Log.e("Test","isSuccess Play = $isSuccess")
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

