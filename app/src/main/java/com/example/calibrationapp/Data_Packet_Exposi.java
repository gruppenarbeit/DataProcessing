package com.example.calibrationapp;

/**
 * Created by Remo on 18.10.2016.
 */
public class Data_Packet_Exposi extends Packet_Exposimeter{

    Data_Packet_Exposi(byte[] packet_in){
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

    public byte[] get_sequence_numberB(){
        return split_packet(12, 15);
    }

    public int get_sequence_number(){
        return byteArray_to_int(get_device_idB());
    }

    public byte[] get_rtc_dataB(){
        return split_packet(16, 21);
    }

    public int[] get_rtc_dataInt(){
        return read_rtc_dataInt(get_rtc_dataB());
    }

    public String get_rtc_dataString(){
        return read_rtc_dataString(get_rtc_dataB());
    }

    public byte get_attenuatorB(){
        return packet[22];
    }

    public int get_attenuator(){
        return (int) get_attenuatorB();
    }

    public byte[] get_frequencyB(){
        return split_packet(23, 24);
    }

    public int get_frequency(){
        return byteArray_to_int(get_frequencyB());
    }

    public byte[] get_rawData_rmsB(){
        return split_packet(25, 28);
    }

    public int get_rawData_rms(){
        return byteArray_to_int(get_rawData_rmsB());
    }

    public byte[] get_rawData_peakB(){
        return split_packet(29, 32);
    }

    public int get_rawData_peak(){
        return byteArray_to_int(get_rawData_rmsB());
    }

    public byte[] get_reserved(){return split_packet(33, 48);}

    public byte get_battery_chargeB(){
        return packet[49];
    }

    public int get_battery_charge(){
        return (int) get_battery_chargeB();
    }

    public byte[] get_battery_voltageB(){
        return split_packet(50, 51);
    }

    public int get_battery_voltage(){
        return byteArray_to_int(get_battery_voltageB());
    }

    public byte[] get_postfixB(){
        return split_packet(52, 55);
    }

    public String get_postfix(){
        return String.valueOf(byteArray_to_charArray(get_postfixB()));
    }

}
