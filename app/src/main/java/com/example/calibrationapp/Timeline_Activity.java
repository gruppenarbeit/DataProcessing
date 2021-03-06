package com.example.calibrationapp;

import java.util.Arrays;

/**
 * Created by Remo on 20.10.2016.
 */
public class Timeline_Activity extends Activity_Superclass {

    Thread thread;
    public double Peak;
    public double RMS;
    public int freq_request;
    WifiDataBuffer buffer;

    Timeline_Activity(WifiDataBuffer buf, final int device_id, final int attenuator, final char measurement_type, int freq){

        this.buffer = buf;
        Peak = 1000;
        RMS = 1000;
        freq_request = freq;
        thread = new Thread(){
            public void run(){
                startMeasurement(device_id, attenuator, measurement_type);
            }
        };
        thread.start();
    }



    public void startMeasurement (int device_id, int attenuator, char measurement_type) {

        Timeline_Packet_Trigger triggerClass = new Timeline_Packet_Trigger(device_id,attenuator, freq_request, measurement_type);
        byte[] triggerPacket = triggerClass.get_packet();

        //successful? => test boolean
        buffer.enqueue_ToESP(triggerPacket);

        while(true) {
            while (!buffer.isDataWaiting_FromESP()) {
                try {
                    wait(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            byte[] packet_in = buffer.deque_FromESP();

            Data_Packet_Exposi packetExposi = new Data_Packet_Exposi(packet_in);

            int freq = packetExposi.get_frequency();
            int rms_exposi = packetExposi.get_rawData_rms();
            int peak_exposi = packetExposi.get_rawData_peak();

            double rms = get_rms(attenuator, freq, rms_exposi);
            double peak = get_peak(attenuator, freq, peak_exposi);
            updatePeak(peak);
            updateRMS(rms);
        }
    }

    public synchronized void updatePeak(double newPeak){
        Peak = newPeak;
    }

    public synchronized void updateRMS(double newRMS){
        RMS = newRMS;
    }

    public synchronized double readPeak(){
        return Peak;
    }

    public synchronized double readRMS(){
        return RMS;
    }

    public synchronized int readFreq(){
        return freq_request;
    }

    public synchronized void stop(){
        thread.stop();
    }
}
