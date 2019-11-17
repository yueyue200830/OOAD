package com.ecnu.ooad;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

/**
 * @author Yiqing Tao
 * @date 2019-11-16 11:02
 */
public class Constants {

    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HIGHT = 600;
    public static final int GAME_WIDTH = 600;
    public static final int GAME_HIGHT = 600;
    public static final int TOOL_WIDTH = 200;
    public static final int TOOL_HIGHT = 600;
    public static final float RATE = 1f;
    public static final float TIME_STEP = 1f/30f;

    public static int mile2Pixel(float mile){
        return (int)(mile*RATE);
    }

}
