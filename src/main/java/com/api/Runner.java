package com.api;

import com.api.constant.Tags;
import com.api.helper.CommonUtils;

import java.util.ArrayList;

public class Runner extends Thread
{
    String runTag;

    public static void main(String[] args)
    {
        ArrayList<Thread> threadsArrayList = new ArrayList<>();

        for (String tag : Tags.TAGS)
        {
            Runnable r1 = new Runner(tag);
            Thread thread = new Thread(r1);
            threadsArrayList.add(thread);
            thread.start();
        }

        for (Thread t: threadsArrayList)
        {
            try
            {
                t.join();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public Runner(String tag)
    {
        this.runTag = tag;
    }

    public void run()
    {
        CommonUtils.runTests(this.runTag);
    }
}
