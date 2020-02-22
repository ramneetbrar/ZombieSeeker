package com.ramneet.zombieseeker.model;

//https://evgenii.com/blog/spring-button-animation-on-android/

/**
 * Takes care of the mathematical operations needed for the scanning animation.
 */
public class MyBounceInterpolator implements android.view.animation.Interpolator {
    private double mAmplitude;
    private double mFrequency;

    public MyBounceInterpolator(double amplitude, double frequency) {
        mAmplitude = amplitude;
        mFrequency = frequency;
    }

    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) *
                Math.cos(mFrequency * time) + 1);
    }
}
