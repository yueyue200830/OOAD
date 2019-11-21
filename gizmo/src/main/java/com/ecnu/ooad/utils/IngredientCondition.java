package com.ecnu.ooad.Utils;

/**
 * @author Yiqing Tao
 * @date 2019-11-21 20:55
 */
public enum IngredientCondition {
    Mouse("mouse", 0),Ball("ball", 1),Absorber("absorber", 2),Slope("slope", 3),Diamond("diamond", 4),Emerald("emerald", 5),StraightTrack("straightTrack", 6),
    CurveTrack("curveTrack", 7),HinderLeft("hinderLeft", 8),HinderRight("hinderRight", 9);

    private String name;
    private int value;

    private IngredientCondition(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
