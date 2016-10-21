package com.example.calibrationapp;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by andre_eggli on 10/3/16.
 * Note that LinkedBlockingQueue<> implements a locking algorithm. No additional synchronisation is required. 
 * If Markus-Thread and Matthü-Thread try to dequeue/enqueue a wifiPackage at the same time, one of the Threads waits till the other is done.
 */

public class WifiDataBuffer {

    LinkedBlockingQueue<byte[]> ToESP; // DataType "WifiPackage" is a seperate class in this package.
    LinkedBlockingQueue<byte[]> FromESP;

    public WifiDataBuffer()  {
        ToESP = new LinkedBlockingQueue<>(10); // Max Size = 10 
        FromESP = new LinkedBlockingQueue<>(100); // Max 100 unprocessed Packages at same time allowed
    }

    public boolean enqueue_ToESP(byte[] packet) {
        return ToESP.add(packet);
    }

    public byte[] dequeue_ToESP(){
        return ToESP.poll();
    }

    public boolean enque_FromESP(byte[] packet) {
//        if (Pack.Content.isEmpty()){ // Do not enqueue empty Strings
//            return true;
//        }
        return FromESP.add(packet);
    }

    public byte[] deque_FromESP() {
        return FromESP.poll();
    }

    public boolean isDataWaiting_ToESP() {
        return !ToESP.isEmpty();
    }

    public boolean isDataWaiting_FromESP() {
        return !FromESP.isEmpty();
    }
}
