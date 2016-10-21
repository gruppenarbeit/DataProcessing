package com.example.calibrationapp;

/**
 * Created by Remo on 20.10.2016.
 */
public class Activity_Superclass {

    public Calibration cali_NormRMS;
    public Calibration cali_NormPeak;
    public Calibration cali_att21RMS;
    public Calibration cali_att21Peak;
    public Calibration cali_att42RMS;
    public Calibration cali_att42Peak;
    public Calibration cali_LNA_RMS;
    public Calibration cali_LNA_Peak;

    public double get_rms(int attenuator, int freq, int rms_exposi){
        if (attenuator == 0){
            return cali_NormRMS.get_field_strength(freq, rms_exposi);
        }
        else if (attenuator == 1){
            return cali_att21RMS.get_field_strength(freq, rms_exposi);
        }
        else if (attenuator == 2){
            return cali_att42RMS.get_field_strength(freq, rms_exposi);
        }
        else return cali_LNA_RMS.get_field_strength(freq, rms_exposi);
    }

    public double get_peak(int attenuator, int freq, int peak_exposi){
        if (attenuator == 0){
            return cali_NormPeak.get_field_strength(freq, peak_exposi);
        }
        else if (attenuator == 1){
            return cali_att21Peak.get_field_strength(freq, peak_exposi);
        }
        else if (attenuator == 2){
            return cali_att42Peak.get_field_strength(freq, peak_exposi);
        }
        else return cali_LNA_Peak.get_field_strength(freq, peak_exposi);
    }
}
