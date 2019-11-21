package com.ecnu.ooad;

import com.ecnu.ooad.Utils.IngredientCondition;
import com.ecnu.ooad.physics.*;
import com.ecnu.ooad.utils.BodyUtil;
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
        isPlayMode = false;
        gamegrids = new GameGrids();
        initBorder();
    }

    private void initBorder() {
        int width = 1;
        int height = Constants.GAME_HEIGHT / 2;
        BodyUtil.initRectangle(height, -width, height * 2, width * 2);
        BodyUtil.initRectangle(height, Constants.GAME_HEIGHT + width, height * 2, width * 2);
        BodyUtil.initRectangle(-width, height, width * 2, height * 2);
        BodyUtil.initRectangle(Constants.GAME_WIDTH + width, height, width * 2, height * 2);
    }

    public void addBall(@NotNull int[] pos) {
        this.addBall(pos, 1);
    }

    public void addBall(@NotNull int[] pos, float scaleRate) {
        int x = pos[0] / Constants.GRID_LENGTH;
        int y = pos[1] / Constants.GRID_LENGTH;

        if (gamegrids.canAddObject(x, y, 1)) {
            Ball ball = new Ball(pos[0], pos[1], scaleRate);
            gamegrids.addObject(x, y, ball);
            ballList.add(ball);
            currentObject = ball;
        }
    }

    public void addTool(int condition, @NotNull int[] pos) {
        this.addTool(condition, pos, 0);
    }

    public void addTool(int condition, @NotNull int[] pos, int direction) {
        this.addTool(condition, pos, direction, 1);
    }

    public void addTool(int condition, @NotNull int[] pos, int direction, float scaleRate) {
        int x = pos[0] / Constants.GRID_LENGTH;
        int y = pos[1] / Constants.GRID_LENGTH;

        if (gamegrids.canAddObject(x, y, condition)) {
            Tool newTool;
            if (condition == 2) {
                // TODO Add hole
                return;
            } else if (condition == IngredientCondition.Slope.getValue()) {
                newTool = new Slope(pos[0], pos[1], 1, direction);
            } else if (condition == IngredientCondition.Diamond.getValue()) {
                newTool = new Diamond(pos[0], pos[1], 1);
            } else if (condition == IngredientCondition.Emerald.getValue()) {
                newTool = new Emerald(pos[0], pos[1], 1);
            } else if (condition == IngredientCondition.StraightTrack.getValue()) {
                newTool = new StraightTrack(pos[0], pos[1], direction, scaleRate);
            } else if (condition == IngredientCondition.CurveTrack.getValue()) {
                newTool = new CurveTrack(pos[0], pos[1], direction, scaleRate);
            } else if (condition == IngredientCondition.HinderLeft.getValue()) {
                newTool = new HinderLeft(pos[0], pos[1], scaleRate);
            } else {
                newTool = new HinderRight(pos[0], pos[1], scaleRate);
            }

            gamegrids.addObject(x, y, newTool);
            toolList.add(newTool);
            currentObject = newTool;
        }
    }

    public void step() {
        world.step(Constants.TIME_STEP, 6, 6);
    }

    public void draw(Graphics2D g) {
        ballList.forEach(it->it.drawMe(g));
        toolList.forEach(it->it.drawMe(g));
    }


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
            this.gamegrids.removeObject(currentObject);
            this.currentObject = null;
        }
    }

    public void rotate() {
        if (currentObject == null) {
            return;
        }

        if (currentObject instanceof CurveTrack || currentObject instanceof StraightTrack || currentObject instanceof Slope) {

            Tool toolObject = (Tool) currentObject;
            int direction = (toolObject.getDirection() + 1) % 4;
            int[] pos = new int[2];
            pos[0] = (int) toolObject.getPositionX();
            pos[1] = (int) toolObject.getPositionY();
            float scaleRate = toolObject.getScaleRate();

            this.deleteObject();

            int condition;
            if (toolObject instanceof CurveTrack) {
                condition = 7;
            } else if (toolObject instanceof StraightTrack) {
                condition = 6;
            } else {
                condition = 3;
            }

            this.addTool(condition, pos, direction, scaleRate);
        }
    }

    public void selectObject(int x, int y) {
        x = x / 20;
        y = y / 20;
        currentObject = gamegrids.getObject(x, y);
    }

    public void changeObjectScale(boolean isZoomIn) {
        if (currentObject == null) {
            return;
        }

        float scaleChange = isZoomIn ? 1 : -1;

        if (currentObject instanceof Tool) {
            float scaleRate = ((Tool) currentObject).getScaleRate() + scaleChange;
            int[] pos = new int[2];
            pos[0] = (int) ((Tool) currentObject).getPositionX();
            pos[1] = (int) ((Tool) currentObject).getPositionY();
            int direction = ((Tool) currentObject).getDirection();
            int type = ((Tool) currentObject).getType();

            this.deleteObject();
            this.addTool(type, pos, direction, scaleRate);
        } else {
            float scaleRate = ((Ball) currentObject).getScaleRate() + scaleChange;
            int[] pos = new int[2];
            pos[0] = (int) ((Ball) currentObject).getPositionX();
            pos[1] = (int) ((Ball) currentObject).getPositionY();

            this.deleteObject();
            this.addBall(pos, scaleRate);
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

    public void setIsPlayMode(boolean isPlayMode) {
        this.isPlayMode = isPlayMode;
    }

    public boolean isPlayMode() {
        return isPlayMode;
    }
}
