package com.example.turingmac.programbox;

/**
 * Created by TuringMac on 2016/6/24.
 */
public class NativeCalc {
    static {
        System.loadLibrary("NativeCalc");
    }

    public native static double calc(String str);
}
