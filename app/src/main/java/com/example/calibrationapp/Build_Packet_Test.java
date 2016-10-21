package com.example.calibrationapp;

import java.util.Arrays;

/**
 * Created by Remo on 15.10.2016.
 */
public class Build_Packet_Test {

    public byte[] test_packet;

    Build_Packet_Test(int length){
        byte[] packet_in = new byte[length];
        Arrays.fill(packet_in, (byte) 'a');
        test_packet= packet_in;
    }
}
