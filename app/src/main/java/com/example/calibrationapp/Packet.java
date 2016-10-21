package com.example.calibrationapp;

import java.math.BigInteger;
import java.nio.ByteBuffer;

/**
 * Created by Remo on 10.10.2016.
 */
public class Packet {

    protected byte[] packet;

    protected byte[] int_to_byteArray (int Integr, int byteArray_length){

        byte[] byteArray = new byte[byteArray_length];
        byte[] bytes = ByteBuffer.allocate(8).putInt(Integr).array();

        for(int i = 0; i < byteArray_length; ++i){
            byteArray[i] = bytes[i];
        }

        return byteArray;
    }

    protected int byteArray_to_int (byte[] byteArray){

        BigInteger value = new BigInteger(byteArray);
        return value.intValue();
    }

    protected int[] byteArray_to_intArray (byte[] byteArray, int bytes_per_int){
        byte[] oneInt = new byte[bytes_per_int];
        int[] result = new int[byteArray.length/bytes_per_int];
        for (int i = 0; i < byteArray.length/bytes_per_int; ++i){
            for (int j = 0; j < bytes_per_int; ++j){
                oneInt[j] = byteArray[i + j];
            }
            result[i] = byteArray_to_int(oneInt);
        }
        return result;
    }

    protected byte[] charArray_to_byteArray (char[] CharArray){

        byte[] byteArray = new byte[CharArray.length];

        for (int i = 0; i < CharArray.length; i++){

            byteArray[i]=(byte)CharArray[i];
        }
        return byteArray;
    }

    protected char[] byteArray_to_charArray (byte[] byteArray){

        char[] charArray = new char[byteArray.length];

        for (int i = 0; i < charArray.length; i++){

            charArray[i]=(char)byteArray[i];
        }
        return charArray;
    }

    public byte[] get_packet(){
        return packet;
    }
}
