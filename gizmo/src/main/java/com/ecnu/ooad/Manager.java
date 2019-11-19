package com.ecnu.ooad;

import com.ecnu.ooad.physics.Ball;
import com.ecnu.ooad.physics.Tool;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Vector;

/**
 * @author Jiayi Zhu, Yiqing Tao
 * @date 2019-11-16 20:33
 */
public class Manager {
    public static World world = new World(new Vec2(0f,10f));
    private Vector<Ball> ballList;
    private Vector<Tool> toolList;
    private int ingredientCondition;
    private int direction;
    private boolean isPlayMode;
    private GameGrids gamegrids;
    private Object currentObject;

    /**
     * mouse: 0, ball: 1, absorber: 2, slope: 3, diamond: 4, emerald: 5, straightTrack: 6, curveTrack: 7,
     * hinderLeft: 8, hinderRight: 9
     */
    public Manager() {
        ballList = new Vector<>();
        toolList = new Vector<>();
        direction = 0;
        isPlayMode = false;
        gamegrids = new GameGrids();
    }

    public void addBall(Ball ball, @NotNull int[] pos) {
        int x = pos[0] / Constants.GRID_LENGTH;
        int y = pos[1] / Constants.GRID_LENGTH;

        if (gamegrids.addObject(x, y, ball)) {
            ballList.add(ball);
            currentObject = ball;
        }
    }

    public void addTool(Tool tool, @NotNull int[] pos) {
        int x = pos[0] / Constants.GRID_LENGTH;
        int y = pos[1] / Constants.GRID_LENGTH;

        if (gamegrids.addObject(x, y, tool)) {
            toolList.add(tool);
            currentObject = tool;
        }
    }

    public void step() {
        world.step(Constants.TIME_STEP, 6, 6);
    }

    public void draw(Graphics2D g) {
        ballList.forEach(it->it.drawMe(g));
        toolList.forEach(it->it.drawMe(g));
    }

    public void setIngredientCondition(int condition) {
        this.ingredientCondition = condition;
    }

    public int getIngredientCondition() {
        return ingredientCondition;
    }

    public void rotate() {
        this.direction = (this.direction + 1) % 4;
    }

    public void selectObject(int x, int y) {
        Object obj = gamegrids.getObject(x, y);
        if (obj != null) {
            currentObject = obj;
        }
    }

    public int getDirection() {
        return direction;
    }

    public void setIsPlayMode(boolean isPlayMode) {
        this.isPlayMode = isPlayMode;
    }

    public boolean isPlayMode() {
        return isPlayMode;
    }
}
