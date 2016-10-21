package com.example.calibrationapp;

/**
 * Created by Remo on 07.10.2016.
 */
public class DetailView_Packet_Trigger extends Packet_Trigger {


    public DetailView_Packet_Trigger(int device_id_in, int attenuator_in, int[] frequencies_in, char measurement_type_in){

        byte[] prefixB = init_prefix();

        //packetType = "DETV";
        char[] packetType_in = {'D', 'E', 'T', 'V'};
        byte[] packetTypeB = charArray_to_byteArray(packetType_in);

        //device_id = device_id_in;
        byte[] device_idB = int_to_byteArray(device_id_in, 4);

        //attenuator = attenuator_in;
        byte attenuatorB = (byte) attenuator_in;

        //frequency_number = frequencies_in.length;
        byte frequency_numberB = (byte) frequencies_in.length;


        //frequencies = frequencies_in;
        byte[] frequenciesB = new byte[2 * frequency_numberB];
        byte[] one_freq = new byte[2];
        for (int i = 0; i < frequenciesB.length; i++){

            one_freq = int_to_byteArray(frequencies_in[i], 2);
            frequenciesB[i] = one_freq[0];
            frequenciesB[i + 1] = one_freq[1];
            ++i;
        }

        //measurement_type = measurement_type_in;
        byte measurement_typeB = (byte) measurement_type_in;

        byte[] reserved = new byte[6];

        byte[] postfixB = init_postfix();

        int packet_length = prefixB.length + packetTypeB.length + device_idB.length + 1 + 1 +frequenciesB.length + 1 + reserved.length + postfixB.length;

        build_packet(prefixB, packetTypeB, device_idB, attenuatorB, frequency_numberB, frequenciesB, measurement_typeB, reserved, postfixB, packet_length);
    }

    private void build_packet (byte[] prefixB, byte[] packetTypeB, byte[] device_idB,
                                          byte attenuatorB, byte frequency_numberB, byte[] frequenciesB,
                                          byte measurement_typeB, byte[] reserved, byte[] postfixB, int packet_length){

        byte[] packet_in = new byte[packet_length];
        int current_index = 0;

        fill_packet(current_index, prefixB, packet_in);
        current_index += prefixB.length;

        fill_packet(current_index, packetTypeB, packet_in);
        current_index += packetTypeB.length;

        fill_packet(current_index, device_idB, packet_in);
        current_index += device_idB.length;

        packet_in[current_index] = attenuatorB;
        current_index += 1;

        packet_in[current_index] = frequency_numberB;
        current_index += 1;

        fill_packet(current_index, frequenciesB, packet_in);
        current_index += frequenciesB.length;

        packet_in[current_index] = measurement_typeB;
        current_index += 1;

        fill_packet(current_index, reserved, packet_in);
        current_index += reserved.length;

        fill_packet(current_index, postfixB, packet_in);
        current_index += postfixB.length;

        packet = packet_in;
    }

}
