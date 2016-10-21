package com.example.calibrationapp;

/**
 * Created by Remo on 07.10.2016.
 */
public class Cal_Packet_Trigger extends Packet_Trigger{

    public Cal_Packet_Trigger(int device_id_in, int attenuator_in)  {

        byte[] prefixB = init_prefix();

        //packetType = "CALD";
        char[] packetType_in = {'C', 'A', 'L', 'D'};
        byte[] packetTypeB = charArray_to_byteArray(packetType_in);

        //device_id = device_id_in;
        byte[] device_idB = int_to_byteArray(device_id_in, 4);

        //attenuator = attenuator_in;
        byte attenuatorB = (byte) attenuator_in;

        byte[] reserved = new byte[15];

        byte[] postfixB = init_postfix();

        int packet_length = prefixB.length + packetTypeB.length + device_idB.length + 1 + reserved.length + postfixB.length;

        build_packet (prefixB, packetTypeB, device_idB, attenuatorB, reserved, postfixB, packet_length);

    }

    private void build_packet(byte[] prefixB, byte[] packetTypeB, byte[] device_idB,
                                           byte attenuatorB, byte[] reserved,
                                           byte[] postfixB, int packet_length){

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

        fill_packet(current_index, reserved, packet_in);
        current_index += reserved.length;

        fill_packet(current_index, postfixB, packet_in);
        current_index += postfixB.length;

        packet = packet_in;
    }

}
