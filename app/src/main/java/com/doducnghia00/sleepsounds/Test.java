package com.doducnghia00.sleepsounds;

import android.widget.ImageButton;

import com.doducnghia00.sleepsounds.model.Sound;

import java.util.ArrayList;
import java.util.List;

public class Test {


    public static void main(String[] args) {
        List<Sound> list = new ArrayList<>();
        Sound sound = new Sound(1,"Mot",1,1);
        list.add(sound);
    }

}
