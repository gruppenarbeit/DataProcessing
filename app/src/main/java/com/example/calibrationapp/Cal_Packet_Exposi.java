package com.example.calibrationapp;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Remo on 11.10.2016.
 */
public class Cal_Packet_Exposi extends Packet_Exposimeter {

    Cal_Packet_Exposi(byte[] packet_in){
        packet = packet_in;
    }


    /*
    methods for accessing the data either in bytes or the corresponding data type
     */
    public byte[] get_prefixB(){
        return split_packet(0, 3);
    }

    public String get_prefix(){
        return String.valueOf(byteArray_to_charArray(get_prefixB()));
    }

    public byte[] get_packetTypeB(){
        return split_packet(4, 7);
    }

    public String get_packetType(){
        return String.valueOf(byteArray_to_charArray(get_packetTypeB()));
    }

    public byte[] get_device_idB(){
        return split_packet(8, 11);
    }

    public int get_device_id(){
        return byteArray_to_int(get_device_idB());
    }

    public byte[] get_device_nameB(){
        return split_packet(12, 75);
    }

    public String get_device_name(){
        return String.valueOf(byteArray_to_charArray(get_device_nameB()));
    }

    public byte[] get_rtc_dataB(){
        return split_packet(76, 81);
    }

    public int[] get_rtc_dataInt(){
        return read_rtc_dataInt(get_rtc_dataB());
    }

    public String get_rtc_dataString(){
        return read_rtc_dataString(get_rtc_dataB());
    }

    public byte get_attenuatorB(){
        return packet[82];
    }

    public int get_attenuator(){
        return (int) get_attenuatorB();
    }

    public byte get_table_numberB(){
        return packet[83];
    }

    public int get_table_number(){
        return (int) get_table_numberB();
    }

    public byte[] get_frequency_numberB(){
        return split_packet(84, 85);
    }

    public int get_frequency_number(){
        return byteArray_to_int(get_frequency_numberB());
    }

    public byte[] get_measurement_numberB(){
        return split_packet(86, 87);
    }

    public int get_measurement_number(){
        return byteArray_to_int(get_frequency_numberB());
    }

    //table_number indicates which table to choose (1 to 8)
    //returns a 0 array if table is empty
    public byte[] get_table(int table_number){
        int table_length = 6666;
        if (get_table_number() < table_number){
            byte[] problem = new byte[table_length];
            Arrays.fill(problem, (byte) 0);
            return problem;
        }
        int start = 88 + table_length * (table_number - 1);
        int end = start + table_length - 1;
        return split_packet(start, end);
    }

    public byte get_table_measurement_typeB(int table_number){
        byte[] table = get_table(table_number);
        return table[0];
    }

    public char get_table_measurement_type(int table_number){
        return (char) get_table_measurement_typeB(table_number);
    }

    public byte get_table_attenuatorB(int table_number){
        byte[] table = get_table(table_number);
        return table[1];
    }

    public int get_table_attenuator(int table_number){
        return (int) get_table_attenuatorB(table_number);
    }

    public byte[] get_table_cali_data(int table_number){
        byte[] table = get_table(table_number);
        return split_packet(2, 6665, table);
    }

    public int[][] get_caliTable(int table_number){

        byte[] table_data = get_table_cali_data(table_number);
        byte[] freq_listB = split_packet(2,1 + 2*get_frequency_number(), table_data);
        byte[] pow_levelsB = split_packet(202, 201 + 4*get_measurement_number(), table_data);
        byte[] raw_data = split_packet(266, 6665, table_data);

        int[] freq_list = byteArray_to_intArray(freq_listB, 2);
        int[] pow_levels = byteArray_to_intArray(pow_levelsB, 4);
        int[][] caliTable = new int[freq_list.length][pow_levels.length];

        for (int i = 0; i < freq_list.length; ++i){
            caliTable[i][0] = freq_list[i];
        }

        for (int j = 0; j < pow_levels.length; ++j){
            caliTable[0][j] = pow_levels[j];
        }

        for (int i = 0; i < freq_list.length; ++i){
            int[] dummy = new int[pow_levels.length];
            dummy = byteArray_to_intArray(split_packet(i * 64, i * 64 + pow_levelsB.length - 1), 4);
            for(int j = 0; j < pow_levels.length; ++j){
                caliTable[i][j] = dummy[j];
            }
        }

        return caliTable;

    }

    public byte get_battery_chargeB(){
        return packet[53416];
    }

    public int get_battery_charge(){
        return (int) get_battery_chargeB();
    }

    public byte[] get_battery_voltageB(){
        return split_packet(53417, 53418);
    }

    public int get_battery_voltage(){
        return byteArray_to_int(get_battery_voltageB());
    }

    public byte[] get_postfixB(){
        return split_packet(53419, 53422);
    }

    public String get_postfix(){
        return String.valueOf(byteArray_to_charArray(get_postfixB()));
    }



}
