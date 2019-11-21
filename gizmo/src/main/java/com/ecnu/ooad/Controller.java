package com.ecnu.ooad;

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

    public Controller() {
        this.newGame();
    }

    public void setIngredientCondition(int condition) {
        this.ingredientCondition = condition;
    }

    public List<Object> getObjectList() {
        return this.manager.getObjectList();
    }

    public void newGame() {
        this.manager = new Manager();
        this.isPlayMode = false;
        this.ingredientCondition = 0;
    }

    public boolean isPlayMode() {
        return isPlayMode;
    }

    public int getIngredientCondition() {
        return ingredientCondition;
    }

    public void addTool(int condition, int[] position) {
        this.manager.addTool(condition, position);
    }

    public void addTool(int condition, int[] position, int direction) {
        this.manager.addTool(condition, position, direction);
    }

    public void setPlayMode(boolean playMode) {
        isPlayMode = playMode;
    }

    public void draw(Graphics2D g) {
        this.manager.draw(g);
    }

    public void changeObjectScale(boolean isZoomIn) {
        this.manager.changeObjectScale(isZoomIn);
    }

    public void rotate() {
        this.manager.rotate();
    }

    public void step() {
        this.manager.step();
    }

    public void deleteObject() {
        this.manager.deleteObject();
    }

    public void addBall(int[] position) {
        this.manager.addBall(position);
    }

    public void selectObject (int x, int y) {
        this.manager.selectObject(x, y);
    }

    // 1: left, 2: right
    public void moveHinder(int key, boolean isRightHinder) {
        this.manager.moveHinder(key, isRightHinder);
    }
}
