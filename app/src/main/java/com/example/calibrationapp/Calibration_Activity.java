package com.example.calibrationapp;

/**
 * Created by Remo on 20.10.2016.
 */
public class Calibration_Activity extends Activity_Superclass{

    Thread thread;
    WifiDataBuffer buffer;

    Calibration_Activity(WifiDataBuffer buf, final int device_id, final int attenuator){
        this.buffer = buf;
        thread = new Thread(){
            public void run(){
                set_calibration(device_id, attenuator);
            }
        };
        thread.start();
    }

    public void set_calibration(int device_id, int attenuator){
        Cal_Packet_Trigger calTrigger = new Cal_Packet_Trigger(device_id, attenuator);
        byte[] triggerPacket = calTrigger.get_packet();

        while (!buffer.isDataWaiting_FromESP()) {
            try {
                wait(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        byte[] packet_in = buffer.deque_FromESP();

        Cal_Packet_Exposi exposiPacket = new Cal_Packet_Exposi(packet_in);
        init_tables(packet_in);
    }

    public void init_tables(byte[] packet_in){

        Cal_Packet_Exposi exposiPacket = new Cal_Packet_Exposi(packet_in);

        char type1 = exposiPacket.get_table_measurement_type(1);
        char type2 = exposiPacket.get_table_measurement_type(2);
        char type3 = exposiPacket.get_table_measurement_type(3);
        char type4 = exposiPacket.get_table_measurement_type(4);
        char type5 = exposiPacket.get_table_measurement_type(5);
        char type6 = exposiPacket.get_table_measurement_type(6);
        char type7 = exposiPacket.get_table_measurement_type(7);
        char type8 = exposiPacket.get_table_measurement_type(8);
        int att1 = exposiPacket.get_table_attenuator(1);
        int att2 = exposiPacket.get_table_attenuator(2);
        int att3 = exposiPacket.get_table_attenuator(3);
        int att4 = exposiPacket.get_table_attenuator(4);
        int att5 = exposiPacket.get_table_attenuator(5);
        int att6 = exposiPacket.get_table_attenuator(6);
        int att7 = exposiPacket.get_table_attenuator(7);
        int att8 = exposiPacket.get_table_attenuator(8);

        save_table(type1, att1, exposiPacket.get_caliTable(1));
        save_table(type2, att2, exposiPacket.get_caliTable(2));
        save_table(type3, att3, exposiPacket.get_caliTable(3));
        save_table(type4, att4, exposiPacket.get_caliTable(4));
        save_table(type5, att5, exposiPacket.get_caliTable(5));
        save_table(type6, att6, exposiPacket.get_caliTable(6));
        save_table(type7, att7, exposiPacket.get_caliTable(7));
        save_table(type8, att8, exposiPacket.get_caliTable(8));
    }

    public void save_table(char type, int attenuator, int[][] cali_table){
        if(type == 'P'){
            if(attenuator == 0){
                cali_NormPeak = new Calibration(cali_table);
            }
            else if(attenuator == 1){
                cali_att21Peak = new Calibration(cali_table);
            }
            if(attenuator == 2){
                cali_att42Peak = new Calibration(cali_table);
            }
            else if(attenuator == 3){
                cali_LNA_Peak = new Calibration(cali_table);
            }
        }
        else if (type == 'R'){
            if(attenuator == 0){
                cali_NormRMS = new Calibration(cali_table);
            }
            else if(attenuator == 1){
                cali_att21RMS = new Calibration(cali_table);
            }
            if(attenuator == 2){
                cali_att42RMS = new Calibration(cali_table);
            }
            else if(attenuator == 3){
                cali_LNA_RMS = new Calibration(cali_table);
            }
        }
    }

    public synchronized void stop(){
        thread.stop();
    }
}
