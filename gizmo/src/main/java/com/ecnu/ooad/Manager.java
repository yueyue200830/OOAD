package com.ecnu.ooad;

import com.ecnu.ooad.utils.IngredientCondition;
import com.ecnu.ooad.physics.*;
import com.ecnu.ooad.utils.BodyUtil;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Vector;

/**
 * @author Jiayi Zhu, Yiqing Tao
 * @date 2019-11-16 20:33
 */
public class Manager {
    public static World world = new World(new Vec2(0f,5f));
    private Vector<Ball> ballList;
    private Vector<Tool> toolList;
    private GameGrids gamegrids;
    private Substance currentObject;
    private HinderRight rightHinder;
    private HinderLeft leftHinder;

    /**
     * This is a class that manages all objects.
     */
    public Manager() {
        ballList = new Vector<>();
        toolList = new Vector<>();
        gamegrids = new GameGrids();
        initBorder();
    }

    /**
     * Add border for the panel.
     */
    private void initBorder() {
        int width = 1;
        int height = Constants.GAME_HEIGHT / 2;
        BodyUtil.initRectangle(height, -width, height * 2, width * 2);
        BodyUtil.initRectangle(height, Constants.GAME_HEIGHT + width, height * 2, width * 2);
        BodyUtil.initRectangle(-width, height, width * 2, height * 2);
        BodyUtil.initRectangle(Constants.GAME_WIDTH + width, height, width * 2, height * 2);
    }

    /**
     * Add a ball based on the position with scale rate is 1.
     * @param pos The position vector.
     */
    public void addBall(@NotNull int[] pos) {
        this.addBall(pos, 1);
    }

    /**
     * Add a ball based on the position and scale rate.
     * @param pos The position vector.
     * @param scaleRate The scale rate.
     */
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

    /**
     * Add a tool based on the position with direction to be 0 and scale rate to be 1.
     * @param condition The type of the tool.
     * @param pos The position vector.
     */
    public void addTool(int condition, @NotNull int[] pos) {
        this.addTool(condition, pos, 1, 0);
    }

    /**
     * Add a tool based on the position and scale rate with scale rate to be 1.
     * @param condition The type of the tool.
     * @param pos The position vector.
     * @param scaleRate Scale Rate.
     */
    public void addTool(int condition, @NotNull int[] pos, float scaleRate) {
        this.addTool(condition, pos, scaleRate, 0);
    }

    /**
     * Add a tool based on the position, direction nad scale rate.
     * @param condition The type of the tool.
     * @param pos The position vector.
     * @param scaleRate The scale rate of the tool.
     * @param direction The direction of the tool.
     */
    public void addTool(int condition, @NotNull int[] pos, float scaleRate, int direction) {
        int x = pos[0] / Constants.GRID_LENGTH;
        int y = pos[1] / Constants.GRID_LENGTH;

        if (gamegrids.canAddObject(x, y, condition)) {
            Tool newTool;
            if (condition == IngredientCondition.Absorber.getValue()) {
                newTool = new Hole(pos[0], pos[1], scaleRate);
            } else if (condition == IngredientCondition.Slope.getValue()) {
                newTool = new Slope(pos[0], pos[1], scaleRate, direction);
            } else if (condition == IngredientCondition.Diamond.getValue()) {
                newTool = new Diamond(pos[0], pos[1], scaleRate);
            } else if (condition == IngredientCondition.Emerald.getValue()) {
                newTool = new Emerald(pos[0], pos[1], scaleRate);
            } else if (condition == IngredientCondition.StraightTrack.getValue()) {
                newTool = new StraightTrack(pos[0], pos[1], direction, scaleRate);
            } else if (condition == IngredientCondition.CurveTrack.getValue()) {
                newTool = new CurveTrack(pos[0], pos[1], direction, scaleRate);
            } else if (condition == IngredientCondition.HinderLeft.getValue()) {
                newTool = new HinderLeft(pos[0], pos[1]);
                leftHinder = (HinderLeft) newTool;
            } else {
                newTool = new HinderRight(pos[0], pos[1]);
                rightHinder = (HinderRight) newTool;
            }

            gamegrids.addObject(x, y, newTool);
            toolList.add(newTool);
            currentObject = newTool;
        }
    }

    /**
     * Move a step for all moving objects.
     */
    public void step() {
        java.util.ArrayList<Hole> holeList = new ArrayList<>();
        java.util.ArrayList<Ball> deleteBallList = new ArrayList<>();

        for (Tool tool:toolList) {
            if (tool instanceof Hole) {
                holeList.add((Hole) tool);
            }
        }
        for (Ball ball: ballList) {
            float ballX = ball.getBallX();
            float ballY = ball.getBallY();
            for (Hole hole: holeList) {
                if (hole.attach(ballX, ballY)) {
                    deleteBallList.add(ball);
                    break;
                }
            }
        }
        for (Ball deleteBall : deleteBallList) {
            currentObject = deleteBall;
            this.deleteObject();
        }
        world.step(Constants.TIME_STEP, 6, 6);
    }

    /**
     * Delete current object if exists.
     */
    public void deleteObject() {
        if (currentObject == null) {
            return;
        }

        Body[] bodyList = currentObject.getBodies();
        for (Body body : bodyList) {
            Manager.world.destroyBody(body);
        }
        this.toolList.removeElement(currentObject);
        this.gamegrids.removeObject(currentObject);
        this.ballList.removeElement(currentObject);
        this.currentObject = null;
    }

    /**
     * Rotate current object if exists.
     */
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

            this.addTool(condition, pos, scaleRate, direction);
        }
    }

    /**
     * Select and object based on the position.
     * @param x Position x.
     * @param y Position y.
     */
    public void selectObject(int x, int y) {
        x = x / 20;
        y = y / 20;
        currentObject = gamegrids.getObject(x, y);
    }

    /**
     * Zoom in / out current object.
     * @param isZoomIn Whether to zoom in the object.
     */
    public void changeObjectScale(boolean isZoomIn) {
        if (currentObject == null) {
            return;
        }

        float scaleChange = isZoomIn ? 1 : -1;
        float scaleRate = currentObject.getScaleRate() + scaleChange;
        int[] pos = new int[2];
        pos[0] = (int) currentObject.getPositionX();
        pos[1] = (int) currentObject.getPositionY();
        int direction = currentObject.getDirection();
        int type = currentObject.getType();

        if (currentObject instanceof Tool) {
            this.deleteObject();
            this.addTool(type, pos, scaleRate, direction);
        } else {
            this.deleteObject();
            this.addBall(pos, scaleRate);
        }
    }

    /**
     * Move right / left hinder.
     * @param key 1 for move left and 2 for move right.
     * @param isRightHinder whether it is right hinder.
     */
    public void moveHinder(int key, boolean isRightHinder) {
        Hinder hinder;
        if (isRightHinder) {
            hinder = rightHinder;
        } else {
            hinder = leftHinder;
        }
        if (hinder == null) {
            return;
        }

        int[] pos = new int[2];

        if (key == 1) {
            pos[0] = (int) hinder.getPositionX() - Constants.GRID_LENGTH;
            pos[1] = (int) hinder.getPositionY();
        } else {
            pos[0] = (int) hinder.getPositionX() + Constants.GRID_LENGTH;
            pos[1] = (int) hinder.getPositionY();
        }
        if (gamegrids.canAddObject(pos[0] / Constants.GRID_LENGTH, pos[1] / Constants.GRID_LENGTH)) {
            currentObject = hinder;
            deleteObject();
            int condition = isRightHinder ? 9 : 8;
            this.addTool(condition, pos, 1);
        }
    }

    /**
     * This function is to start a new game.
     */
    public void clearGame() {
        for (Ball ball : ballList) {
            world.destroyBody(ball.getBodies()[0]);
        }
        for (Tool tool : toolList) {
            for (Body body : tool.getBodies()) {
                world.destroyBody(body);
            }
        }
        ballList.clear();
        toolList.clear();
        gamegrids = new GameGrids();
        currentObject = null;
        rightHinder = null;
        leftHinder = null;
    }

    /**
     * Get the list of all objects.
     * @return A list of current objects.
     */
    public java.util.List<Object> getObjectList() {
        java.util.List<Object> objectList = new ArrayList<>();
        objectList.addAll(this.ballList);
        objectList.addAll(this.toolList);
        return objectList;
    }

    /**
     * Get object details to draw in frontend.
     * @return List of object detail.
     */
    public Vector<JSONObject> getObjectDetail() {
        Vector<JSONObject> objectList = new Vector<>();
        for (Tool tool : toolList) {
            objectList.add(tool.getCurrentDetail());
        }
        for (Ball ball : ballList) {
            objectList.add(ball.getCurrentDetail());
        }
        return objectList;
    }
}
