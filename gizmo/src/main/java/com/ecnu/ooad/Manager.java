package com.ecnu.ooad;

import com.ecnu.ooad.physics.Ball;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import java.awt.*;
import java.util.Vector;

/**
 * @author Jiayi Zhu
 * @date 2019-11-16 20:33
 */
public class Manager {
    public static World world = new World(new Vec2(0f,10f));
    Vector<Ball> ballList;

    public Manager(){
        ballList = new Vector<>();
        initObjs();
    }

    private void initObjs(){
        Ball ball = new Ball(42f,50f);
        ballList.add(ball);
    }


    public void step(){
        world.step(Constants.TIME_STEP,6,6);
    }

    public void draw(Graphics2D g){
        ballList.forEach(it->it.drawMe(g));
    }
}
