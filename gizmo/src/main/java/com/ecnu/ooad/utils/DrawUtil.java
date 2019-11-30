package com.ecnu.ooad.utils;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * @author Jiayi Zhu
 * @date 2019-11-22 16:38
 */
public class DrawUtil {

    /**
     * Draw objects in the panel.
     * @param objectDetails jsonobject list.
     * @param g Graphics g.
     * @param panel Drawing panel.
     */
    public static void drawObjects(@NotNull Vector<JSONObject> objectDetails, Graphics2D g, JPanel panel) {
        for (JSONObject objectDetail : objectDetails) {
            int condition = objectDetail.getInt("condition");

            if (condition == IngredientCondition.Ball.getValue()) {
                DrawUtil.drawBall(objectDetail, g, panel);

            } else if (condition == IngredientCondition.Absorber.getValue()) {
                DrawUtil.drawAbsorber(objectDetail, g, panel);

            } else if (condition == IngredientCondition.Slope.getValue()) {
                DrawUtil.drawSlope(objectDetail, g);

            } else if (condition == IngredientCondition.Diamond.getValue()) {
                DrawUtil.drawDiamond(objectDetail, g, panel);

            } else if (condition == IngredientCondition.Emerald.getValue()) {
                DrawUtil.drawEmerald(objectDetail, g, panel);

            } else if (condition == IngredientCondition.StraightTrack.getValue()) {
                DrawUtil.drawStraightTrack(objectDetail, g);

            } else if (condition == IngredientCondition.CurveTrack.getValue()) {
                DrawUtil.drawCurveTrack(objectDetail, g);

            } else if (condition == IngredientCondition.HinderLeft.getValue() || condition == IngredientCondition.HinderRight.getValue()) {
                DrawUtil.drawHinder(objectDetail, g, panel);

            }
        }
    }

    /**
     * Draw ball.
     * @param objectDetail details.
     * @param g Graphics g.
     * @param panel Drawing panel.
     */
    public static void drawBall(@NotNull JSONObject objectDetail, @NotNull Graphics2D g, JPanel panel) {
        int x = objectDetail.getInt("x");
        int y = objectDetail.getInt("y");
        int delimiter = objectDetail.getInt("delimiter");

        Image image = Toolkit.getDefaultToolkit().getImage("src/main/resources/ball.png");
        g.drawImage(image, x, y, delimiter, delimiter, panel);
    }

    /**
     * Draw absorber.
     * @param objectDetail details.
     * @param g Graphics g.
     * @param panel Drawing panel.
     */
    public static void drawAbsorber(@NotNull JSONObject objectDetail, @NotNull Graphics2D g, JPanel panel) {
        int x = objectDetail.getInt("x");
        int y = objectDetail.getInt("y");
        int width = objectDetail.getInt("width");
        int height = objectDetail.getInt("height");

        Image image = Toolkit.getDefaultToolkit().getImage("src/main/resources/hole.png");
        g.drawImage(image, x, y, width, height, panel);
    }

    /**
     * Draw slope.
     * @param objectDetail details.
     * @param g Graphics g.
     */
    public static void drawSlope(@NotNull JSONObject objectDetail, @NotNull Graphics2D g) {
        int[] x = (int[]) objectDetail.get("x");
        int[] y = (int[]) objectDetail.get("y");

        g.setColor(new Color(252, 149, 12));
        g.fillPolygon(x, y, 3);
    }

    /**
     * Draw diamond.
     * @param objectDetail details.
     * @param g Graphics g.
     * @param panel Drawing panel.
     */
    public static void drawDiamond(@NotNull JSONObject objectDetail, @NotNull Graphics2D g, JPanel panel) {
        int x = objectDetail.getInt("x");
        int y = objectDetail.getInt("y");
        int delimiter = objectDetail.getInt("delimiter");

        Image image = Toolkit.getDefaultToolkit().getImage("src/main/resources/circle.png");
        g.drawImage(image, x, y, delimiter, delimiter, panel);
    }

    /**
     * Draw emerald.
     * @param objectDetail details.
     * @param g Graphics g.
     * @param panel Drawing panel.
     */
    public static void drawEmerald(@NotNull JSONObject objectDetail, @NotNull Graphics2D g, JPanel panel) {
        int x = objectDetail.getInt("x");
        int y = objectDetail.getInt("y");
        int width = objectDetail.getInt("width");
        int height = objectDetail.getInt("height");

        Image image = Toolkit.getDefaultToolkit().getImage("src/main/resources/square.png");
        g.drawImage(image, x, y, width, height, panel);
    }

    /**
     * Draw straight track.
     * @param objectDetail details.
     * @param g Graphics g.
     */
    public static void drawStraightTrack(@NotNull JSONObject objectDetail, @NotNull Graphics2D g) {
        int width = objectDetail.getInt("width");
        int height = objectDetail.getInt("height");
        int x1 = objectDetail.getInt("x1");
        int x2 = objectDetail.getInt("x2");
        int y1 = objectDetail.getInt("y1");
        int y2 = objectDetail.getInt("y2");

        g.setColor(new Color(178, 136, 80));
        g.fillRect(x1, y1, width, height);
        g.fillRect(x2, y2, width, height);
    }

    /**
     * Draw curve track.
     * @param objectDetail details.
     * @param g Graphics g.
     */
    public static void drawCurveTrack(@NotNull JSONObject objectDetail, @NotNull Graphics2D g) {
        g.setColor(new Color(178, 136, 80));
        Stroke stroke = new BasicStroke(2);
        g.setStroke(stroke);

        int arcX = objectDetail.getInt("arcX");
        int arcY = objectDetail.getInt("arcY");
        int width = objectDetail.getInt("width");
        int height = objectDetail.getInt("height");
        int startAngle = objectDetail.getInt("startAngle");
        int arcAngle = 90;
        int dotX = objectDetail.getInt("dotX");
        int dotY = objectDetail.getInt("dotY");

        g.drawArc(arcX, arcY, width, height, startAngle, arcAngle);
        g.fillRect(dotX, dotY, 2, 2);
    }

    /**
     * Draw hinder.
     * @param objectDetail details.
     * @param g Graphics g.
     * @param panel Drawing panel.
     */
    public static void drawHinder(@NotNull JSONObject objectDetail, @NotNull Graphics2D g, JPanel panel) {
        int x = objectDetail.getInt("x");
        int y = objectDetail.getInt("y");
        int width = objectDetail.getInt("width");
        int height = objectDetail.getInt("height");

        Image image = Toolkit.getDefaultToolkit().getImage("src/main/resources/hinder-draw.png");
        g.drawImage(image, x, y, width, height, panel);
    }
}
