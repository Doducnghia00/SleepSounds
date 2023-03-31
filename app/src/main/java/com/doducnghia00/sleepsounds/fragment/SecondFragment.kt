package com.doducnghia00.sleepsounds.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doducnghia00.sleepsounds.R
import com.doducnghia00.sleepsounds.adapter.SoundAdapter
import com.doducnghia00.sleepsounds.model.Sound


class SecondFragment : Fragment() {

    private var recyclerView : RecyclerView?= null
    private lateinit var soundAdapter : SoundAdapter


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


        soundAdapter = SoundAdapter(requireContext())

        soundAdapter.setData(getListSound())
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView?.adapter = soundAdapter

        val gridLayoutManager = GridLayoutManager(context,4)
        recyclerView?.layoutManager = gridLayoutManager
    }

    private fun getListSound(): List<Sound>? {
        val list: MutableList<Sound> = ArrayList()
        list.add(Sound(1, "Rain", R.drawable.rainy, R.raw.rain_main))
        list.add(Sound(2, "Thunderstorm", R.drawable.thunderstorm, R.raw.thunderstorm))
        list.add(Sound(3, "Rain on leaves", R.drawable.leaf, R.raw.rain_on_leaves))
        list.add(Sound(4, "Wind", R.drawable.air, R.raw.wind))

        list.add(Sound(5, "Rain", R.drawable.rainy, R.raw.rain_main))
        list.add(Sound(6, "Thunderstorm", R.drawable.thunderstorm, R.raw.thunderstorm))
        list.add(Sound(7, "Rain on leaves", R.drawable.leaf, R.raw.rain_on_leaves))
        list.add(Sound(8, "Wind", R.drawable.air, R.raw.wind))


        return list
    }


}