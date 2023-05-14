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

    private var listener: MyFragmentListener? = null
    private lateinit var runningList : BooleanArray




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
        Log.e("Test","Fragment 2")

        soundAdapter = SoundAdapter(requireContext())


        list = getListSound()

        runningList = listener?.getRunningList() ?: BooleanArray(list.size)

        soundAdapter.setData(list, runningList)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView?.adapter = soundAdapter

        val gridLayoutManager = GridLayoutManager(context,4)
        recyclerView?.layoutManager = gridLayoutManager

        soundAdapter.setOnItemClickListener(object : SoundAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Log.e("Test", "Fragment 2 clicked $position")
                listener?.onSoundClick(position)
                soundAdapter.setData(list, listener!!.getRunningList())
            }

        })



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
    override fun onResume() {
        super.onResume()
        //soundAdapter.showStatusMP()
    }

    override fun onPause() {
        super.onPause()
       // soundAdapter.stopAllMP()
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {

    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MyFragmentListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement MyFragmentListener")
        }
    }
    interface MyFragmentListener {
        fun onSoundClick(position: Int)
        fun getRunningList() : BooleanArray
    }



}