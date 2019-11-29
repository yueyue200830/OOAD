package com.ecnu.ooad;

import com.ecnu.ooad.utils.FileManager;
import com.ecnu.ooad.utils.TransformUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Yiqing Tao
 * @date 2019-11-21 19:39
 */
public class Controller {
    private Manager manager;
    private boolean isPlayMode;
    private int ingredientCondition;
    private TransformUtil transformUtil;

    /**
     * This is the controller of the game.
     */
    public Controller() {
        this.manager = new Manager();
        this.transformUtil = new TransformUtil(this);
    }

    /**
     * Set ingredient condition.
     * @param condition Condition to be set.
     */
    public void setIngredientCondition(int condition) {
        this.ingredientCondition = condition;
    }

    /**
     * Get object list.
     * @return The list of th object.
     */
    public List<Object> getObjectList() {
        return this.manager.getObjectList();
    }

    /**
     * Start a new game.
     */
    public void newGame() {
        this.manager.clearGame();
        this.isPlayMode = false;
    }

    /**
     * Get whether it si play mode.
     * @return Yes if it is play mode.
     */
    public boolean isPlayMode() {
        return isPlayMode;
    }

    /**
     * Get ingredient condition.
     * @return The condition of ingredient.
     */
    public int getIngredientCondition() {
        return ingredientCondition;
    }

    /**
     * Add a tool based on the position with direction to be 0 and scale rate to be 1.
     * @param condition The type of the tool.
     * @param position The position vector.
     */
    public void addTool(int condition, int[] position) {
        this.manager.addTool(condition, position);
    }

    /**
     * Add a tool based on the position and scale rate with scale rate to be 1.
     * @param condition The type of the tool.
     * @param position The position vector.
     * @param scaleRate Scale Rate.
     */
    public void addTool(int condition, int[] position, float scaleRate) {
        this.manager.addTool(condition, position, scaleRate);
    }

    /**
     * Add a tool based on the position, direction nad scale rate.
     * @param condition The type of the tool.
     * @param position The position vector.
     * @param scaleRate The scale rate of the tool.
     * @param direction The direction of the tool.
     */
    public void addTool(int condition, int[] position, float scaleRate, int direction) {
        this.manager.addTool(condition, position, scaleRate, direction);
    }

    /**
     * Set play mode.
     * @param playMode Play mode.
     */
    public void setPlayMode(boolean playMode) {
        isPlayMode = playMode;
    }

    /**
     * Draw objects.
     * @param g Graphic g.
     */
    public void draw(Graphics2D g, JPanel panel) {
        this.manager.draw(g, panel);
    }

    /**
     * Change object's scale.
     * @param isZoomIn whether to zoom in.
     */
    public void changeObjectScale(boolean isZoomIn) {
        this.manager.changeObjectScale(isZoomIn);
    }

    /**
     * Rotate the object.
     */
    public void rotate() {
        this.manager.rotate();
    }

    /**
     * Go forward for all objects.
     */
    public void step() {
        this.manager.step();
    }

    /**
     * Delete an object.
     */
    public void deleteObject() {
        this.manager.deleteObject();
    }

    /**
     * Add a ball based on the position with scale rate is 1.
     * @param position The position vector.
     */
    public void addBall(int[] position) {
        this.manager.addBall(position);
    }

    /**
     * Add a ball based on the position and scale rate.
     * @param position The position vector.
     * @param scaleRate The scale rate.
     */
    public void addBall(int[] position, float scaleRate) {
        this.manager.addBall(position, scaleRate);
    }

    /**
     * Select an object based on the position.
     * @param x Position x.
     * @param y Position y.
     */
    public void selectObject (int x, int y) {
        this.manager.selectObject(x, y);
    }

    /**
     * Move right / left hinder.
     * @param key 1 for move left and 2 for move right.
     * @param isRightHinder whether it is right hinder.
     */
    public void moveHinder(int key, boolean isRightHinder) {
        this.manager.moveHinder(key, isRightHinder);
    }

    /**
     * Save current game.
     */
    public void saveGame() {
        String gameConfig = this.transformUtil.objectToJson(this.getObjectList());
        FileManager.saveGame(gameConfig);
    }

    /**
     * Load a file in the game.
     * @param fileName File name.
     */
    public void loadGame(String fileName) {
        String gameConfig = com.ecnu.ooad.utils.FileManager.readGame(fileName);
        this.transformUtil.jsonToObject(gameConfig);
    }
}
