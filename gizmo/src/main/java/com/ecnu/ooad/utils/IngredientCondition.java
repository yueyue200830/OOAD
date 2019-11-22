package com.ecnu.ooad.utils;

import org.jetbrains.annotations.Contract;

/**
 * @author Yiqing Tao
 * @date 2019-11-21 20:55
 */
public enum IngredientCondition {
    /**
     * Mouse
     */
    Mouse("mouse", 0),
    /**
     * Ball
     */
    Ball("ball", 1),
    /**
     * Absorber
     */
    Absorber("absorber", 2),
    /**
     * Slope
     */
    Slope("slope", 3),
    /**
     * Diamond
     */
    Diamond("diamond", 4),
    /**
     * Emerald
     */
    Emerald("emerald", 5),
    /**
     * Straight track
     */
    StraightTrack("straightTrack", 6),
    /**
     * Curve track
     */
    CurveTrack("curveTrack", 7),
    /**
     * Left hinder
     */
    HinderLeft("hinderLeft", 8),
    /**
     * Right hinder
     */
    HinderRight("hinderRight", 9);

    private String name;
    private int value;

    @Contract(pure = true)
    IngredientCondition(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Contract(pure = true)
    public int getValue() {
        return this.value;
    }
}
