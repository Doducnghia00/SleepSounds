package com.doducnghia00.sleepsounds.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doducnghia00.sleepsounds.MainActivity
import com.doducnghia00.sleepsounds.R
import com.doducnghia00.sleepsounds.adapter.SoundAdapter
import com.doducnghia00.sleepsounds.model.Sound


class SecondFragment : Fragment() {

    private var recyclerView : RecyclerView?= null
    private lateinit var soundAdapter : SoundAdapter
    private lateinit var list: List<Sound>
    private var mPlayer : List<Int> = listOf(0, 0, 0, 0)
    private lateinit var mediaPlayer : MediaPlayer
    private lateinit var mediaPlayer1 : MediaPlayer
    private lateinit var mediaPlayer2 : MediaPlayer
    private lateinit var mediaPlayer3 : MediaPlayer

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
        var runningList = BooleanArray(list.size)


        soundAdapter.setData(list)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView?.adapter = soundAdapter

        val gridLayoutManager = GridLayoutManager(context,4)
        recyclerView?.layoutManager = gridLayoutManager

        soundAdapter.setOnItemClickListener(object : SoundAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(context, "Click ${list[position].name}", Toast.LENGTH_LONG).show()
                Log.e("Test","Clicked")

                //If sound is running
                if (runningList[position]){
                    runningList[position] = false
                    stopSound(position)
                }else{ // If sound isn't running
                    runningList[position] = true
                    Toast.makeText(context, playSound(position).toString(),Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun initMedia() {
        mediaPlayer = MediaPlayer.create(context,R.raw.rain_main)
        mediaPlayer.isLooping = true
        mediaPlayer1 = MediaPlayer.create(context,R.raw.rain_main)
        mediaPlayer1.isLooping = true
        mediaPlayer2 = MediaPlayer.create(context,R.raw.rain_main)
        mediaPlayer2.isLooping = true
        mediaPlayer3 = MediaPlayer.create(context,R.raw.rain_main)
        mediaPlayer3.isLooping = true
    }


    // ?????
    private fun playSound(position: Int) :Boolean {
        for (i in mPlayer.indices){
            if (mPlayer[i] == 0){
                when(i){
                    0->{
                        mediaPlayer = MediaPlayer.create(context, list[position].raw)
                        mediaPlayer.start()
                    }
                    1->{
                        mediaPlayer1 = MediaPlayer.create(context, list[position].raw)
                        mediaPlayer1.start()
                    }
                    2->{
                        mediaPlayer2 = MediaPlayer.create(context, list[position].raw)
                        mediaPlayer2.start()
                    }
                    3->{
                        mediaPlayer3 = MediaPlayer.create(context, list[position].raw)
                        mediaPlayer3.start()
                    }
                }
                return true;
                ;
            }
        }
        return false
    }

    private fun stopSound(position: Int) {

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


}