package com.ecnu.ooad;

import com.ecnu.ooad.physics.Ball;
import com.ecnu.ooad.physics.Tool;
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
    private Vector<Ball> ballList;
    private Vector<Tool> toolList;
    private int ingredientCondition;

    /**
     * mouse: 0, ball: 1, absorber: 2, slope: 3, diamond: 4, emerald: 5, straightTrack: 6, curveTrack: 7,
     * hinderLeft: 8, hinderRight: 9
     */
    public Manager() {
        ballList = new Vector<>();
        toolList = new Vector<>();
        initObjs();
    }

    private void initObjs() {
        Ball ball = new Ball(42f, 50f);
        ballList.add(ball);
    }

    public void addBall(Ball ball) {
        this.ballList.add(ball);
    }

    public void addTool(Tool tool) {
        toolList.add(tool);
    }

    public void step(){
        world.step(Constants.TIME_STEP, 6, 6);
    }

    public void draw(Graphics2D g) {
        ballList.forEach(it->it.drawMe(g));
        toolList.forEach(it->it.drawMe(g));
    }

    public void setIngredientCondition(int condition) {
        System.out.println("set condition");
        this.ingredientCondition = condition;
    }

    public int getIngredientCondition() {
        return ingredientCondition;
    }
}
