package com.ecnu.ooad;

import com.ecnu.ooad.physics.*;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
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

    // TODO: add body list for all classes.
    public void deleteObject() {
        if (currentObject == null) {
            return;
        }

        if (currentObject instanceof Tool) {
            Body[] bodyList = ((Tool) currentObject).getBodies();
            for (Body body : bodyList) {
                Manager.world.destroyBody(body);
            }
            this.toolList.removeElement(currentObject);
            this.gamegrids.removeObject(currentObject);
            this.currentObject = null;
        } else {
            Body body = ((Ball) currentObject).getBody();
            Manager.world.destroyBody(body);
            this.ballList.removeElement(currentObject);
            this.gamegrids.removeObject(ballList);
            this.currentObject = null;
        }
    }

    public void rotate() {
        if (currentObject == null) {
            return;
        }

        if (currentObject instanceof CurveTrack || currentObject instanceof StraightTrack || currentObject instanceof Slope) {

            Tool toolObject = (Tool) currentObject;
            float x = toolObject.getPositionX();
            float y = toolObject.getPositionY();
            int direction = (toolObject.getDirection() + 1) % 4;
            int[] pos = new int[2];
            pos[0] = (int) x;
            pos[1] = (int) y;

            this.deleteObject();

            Tool newTool;
            if (toolObject instanceof CurveTrack) {
                newTool = new CurveTrack(x, y, direction);
            } else if (toolObject instanceof StraightTrack) {
                newTool = new StraightTrack(x, y, direction,1);
            } else {
                newTool = new Slope(x, y, 1, direction);
            }
            this.addTool(newTool, pos);
        }
    }

    public void selectObject(int x, int y) {
        Object obj = gamegrids.getObject(x, y);
        if (obj != null) {
            currentObject = obj;
        }
    }

    public Vector<Ball> getBallList() {
        return ballList;
    }

    public Vector<Tool> getToolList() {
        return toolList;
    }

    public java.util.List<Object> getObjectList() {
        java.util.List<Object> objectList = new ArrayList<>();
        objectList.addAll(this.ballList);
        objectList.addAll(this.toolList);
        return objectList;
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
