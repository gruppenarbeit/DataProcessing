package com.example.calibrationapp;

import java.nio.ByteBuffer;

/**
 * Created by Remo on 07.10.2016.
 */

public class Packet_Trigger extends Packet{

    protected byte[] init_prefix(){
        char[] prefix_in = {'R', 'D', '1', '6'};
        byte[] prefixB = new byte[4];
        for (int i = 0; i < prefix_in.length; i++) {
            prefixB[i] = (byte) prefix_in[i];
        }
        return  prefixB;
    }

    protected byte[] init_postfix(){
        char[] postfix_in = {'P', 'E', 'N', 'D'};
        byte[] postfixB = new byte[4];
        for (int i = 0; i < postfix_in.length; i++) {
            postfixB[i] = (byte) postfix_in[i];
        }
        return postfixB;
    }

    protected void fill_packet(int start, byte[] values, byte[] packet_in){

        int end = start + values.length;
        for (int i = 0; start < end; start++){
            packet_in[start] = values[i];
            i++;
        }
    }
}
