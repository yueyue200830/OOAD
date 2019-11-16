package com.ecnu.ooad;

import com.ecnu.ooad.physics.Ball;
import com.ecnu.ooad.physics.Tool;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yiqing Tao
 * @date 2019-11-16 17:01
 */
public class Manager {
    private List<Ball> ballList;
    private List<Tool> toolList;
    public World world;


    public Manager() {
        world = new World(new Vec2(0f,-10f));
        Ball ball = new Ball(42f, 50f);
        BodyDef body = ball.getBd();
        ball.setBall(world.createBody(body));
        ball.setFeaturefd();
        ballList = new ArrayList<>();
        toolList = new ArrayList<>();

        ballList.add(ball);
    }

    public void step() {
        this.world.step(Constants.TIME_STEP,6,6);
        System.out.println("manager!!");
    }

    public void draw(Graphics2D g) {
        ballList.forEach(ball -> ball.drawMe(g));
        //toolList.forEach(tool -> tool.drawMe(g));


    }
}
