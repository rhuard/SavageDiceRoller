package com.example.rhuard.savagediceroller;

import java.util.Random;
import java.util.SimpleTimeZone;

/**
 * Created by rhuard on 9/14/17.
 */

//Savage worlds dice roller (handles raises and wildcards)
public class SavageDiceEngine {
    Random rand;

    public SavageDiceEngine(){
        rand = new Random(System.currentTimeMillis());
    }

    private int RollDie(int size){
        int result = rand.nextInt(size) + 1;
        //check for ace
        if (result == size){
            result += RollDie(size);
        }

        return result;
    }

    private int RollDie(int size, int modifier){
        return RollDie(size) + modifier;
    }

    public int HandleRoll(int size){
        return RollDie(size);
    }

    public int HandleRoll(int size, int modifier){
        return RollDie(size, modifier);
    }

    public int HandleRoll(int size, boolean wildcard){
        //default to 0 because every roll will be over 0
        int wild = 0;
        int regular = RollDie(size);
        int result;
        if(wildcard) {
            wild = RollDie(6);
        }

        if (wild > regular){
            result = wild;
        }else{
            result = regular;
        }

        return result;
    }

    public int HandleRoll(int size, int modifier, boolean wildcard){
        return HandleRoll(size, wildcard) + modifier;
    }
}
