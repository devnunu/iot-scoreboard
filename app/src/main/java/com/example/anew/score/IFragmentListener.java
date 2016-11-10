package com.example.anew.score;

/**
 * Created by new on 2016. 11. 10..
 */

public interface IFragmentListener {
    public static final int CALLBACK_RUN_IN_BACKGROUND = 1;
    public static final int CALLBACK_SEND_MESSAGE = 2;

    public void OnFragmentCallback(int msgType, int arg0, int arg1, String arg2, String arg3, Object arg4);
}