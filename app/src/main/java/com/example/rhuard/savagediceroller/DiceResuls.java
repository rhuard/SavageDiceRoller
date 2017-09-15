package com.example.rhuard.savagediceroller;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by rhuard on 9/15/17.
 */

public interface DiceResuls {
    public Hashtable<String, List<Integer>> HandleRoll(int size);
    public Hashtable<String, List<Integer>> HandleRoll(int size, int modifier);
}
