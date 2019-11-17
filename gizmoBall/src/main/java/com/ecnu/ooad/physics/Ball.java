package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.Manager;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import java.awt.*;

/**
 * @author Yiqing Tao
 * @date 2019-11-14 16:51
 */
public class Ball {
    private float worldX;
    private float worldY;
    private float radius;
    private Body ball;
    //private BodyDef bd;
    private FixtureDef fd;
    private Color color;

    public Ball(float x, float y) {
        this.worldX = x;
        this.worldY = y;
        this.radius = 10;
        this.color = Color.red;
        initBallWorld();
    }

    public int getWidthOrHeight() {
        return Constants.mile2Pixel(this.radius * 2);
    }
    private void initBallWorld() {
        BodyDef bd = new BodyDef();
        bd.position = new Vec2(this.worldX, this.worldY);
        bd.type = BodyType.DYNAMIC;

        fd = new FixtureDef();
        CircleShape cs = new CircleShape();
        cs.setRadius(this.radius);
        fd.shape = cs;
        fd.density = 0.5f;
        fd.friction = 0.3f;
        fd.restitution = 0.9f;
        this.ball = Manager.world.createBody(bd);
        ball.createFixture(this.fd);
    }

    public void setFeaturefd() {
        ball.createFixture(this.fd);
        ball.setGravityScale(10);
        this.ball.setActive(true);
        this.ball.setAwake(true);
        System.out.println(ball.m_gravityScale);
    }

    public void drawMe(Graphics2D g) {
        g.setColor(this.color);
        g.fillOval((int)this.getPixelX(),(int)this.getPixelY(), this.getWidthOrHeight(), this.getWidthOrHeight());
    }

    public float getPixelX() {
        return this.ball.getPosition().x - this.radius;
    }

    public float getPixelY() {
        return this.ball.getPosition().y - this.radius;
    }
    public float getWorldX() {
        return worldX;
    }

    public float getWorldY() {
        return worldY;
    }

    public float getRadius() {
        return radius;
    }

    public Body getBall() {
        return ball;
    }

//    public BodyDef getBd() {
//        return bd;
//    }

    public void setWorldX(float worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(float worldY) {
        this.worldY = worldY;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setBall(Body ball) {
        this.ball = ball;
    }

//    public void setBd(BodyDef bd) {
//        this.bd = bd;
//    }

    public void setFd(FixtureDef fd) {
        this.fd = fd;
    }
}
