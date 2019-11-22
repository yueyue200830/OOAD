package com.ecnu.ooad.physics;

import org.jetbrains.annotations.Contract;

/**
 * @author Yiqing Tao
 * @date 2019-11-14 16:51
 */
public class Tool extends Substance{

    /**
     * This is a parent class for all static tool.
     * @param x Position x.
     * @param y Position y.
     * @param scaleRate Scale rate.
     */
    @Contract(pure = true)
    public Tool(float x, float y, float scaleRate) {
        super(x, y, scaleRate);
        this.isStatic = false;
    }

}
