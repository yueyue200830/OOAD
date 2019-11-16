package com.ecnu.ooad.physics;

import com.ecnu.ooad.Manager;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import java.awt.*;

/**
 * @author Jiayi Zhu
 * @date 2019-11-16 20:34
 */
public class Ball {
    float worldX;
    float worldY;
    float r;
    public Body ballInWorld;
    Color color = Color.cyan;

    public Ball(float worldX,float worldY){
        this.worldX = worldX;
        this.worldY = worldY;
        this.r = 5f;
        initBallInWorld();
    }

    public Ball(){
        this(42f,50f);
    }

    public float getPixelX(){
        return ballInWorld.getPosition().x - r;
    }

    public float getPixelY(){
        return ballInWorld.getPosition().y - r;
    }

    public float getWidthOrHeight(){
        return r*2;
    }

    private void initBallInWorld(){
        BodyDef bd = new BodyDef();
        bd.position = new Vec2(worldX,worldY);
        bd.type = BodyType.DYNAMIC;

        FixtureDef fd = new FixtureDef();
        CircleShape cs = new CircleShape();
        cs.m_radius = r;
        fd.shape = cs;
        fd.density = 0.5f;
        fd.friction = 0.3f;
        fd.restitution = 0.6f;

        ballInWorld = Manager.world.createBody(bd);
        ballInWorld.createFixture(fd);
    }

    public void drawMe(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int)this.getPixelX(), (int)this.getPixelY(), (int)this.getWidthOrHeight(), (int)this.getWidthOrHeight());
    }
}
