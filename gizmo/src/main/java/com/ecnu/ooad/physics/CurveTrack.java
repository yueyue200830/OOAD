package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.Manager;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Yiqing Tao, Jiayi Zhu
 * @date 2019-11-16 10:55
 */
public class CurveTrack extends Track {
    private int x;
    private int y;
    private Body body1;
    private Body body2;
    private Body body3;
    private Color color = Color.yellow;
    private int direction;

    public CurveTrack(float x, float y, int direction) {
        super(x, y);
        this.x = (int) x;
        this.y = (int) y;
        this.direction = direction;
        this.initCurveTrack();
    }

    private void initCurveTrack() {
        float posX = x - Constants.GRID_LENGTH / 2;
        float posY = y - Constants.GRID_LENGTH / 2;
        float edge = (float) (10 - 4 * Math.sqrt(2) + 4f);
        float length = Constants.GRID_LENGTH;
        float dif = length - edge;

        BodyDef bd1 = new BodyDef();
        bd1.position.set(new Vec2(posX, posY));
        bd1.type = BodyType.STATIC;
        BodyDef bd2 = new BodyDef();
        bd2.type = BodyType.STATIC;
        BodyDef bd3 = new BodyDef();
        bd3.type = BodyType.STATIC;

        PolygonShape ps1 = new PolygonShape();
        PolygonShape ps2 = new PolygonShape();
        PolygonShape ps3 = new PolygonShape();
        switch (direction) {
            case 0:
                bd2.position.set(new Vec2(posX + length / 2, posY + 0.5f));
                bd3.position.set(new Vec2(posX + length - 0.5f, posY + length / 2));

                ps1.set(new Vec2[]{new Vec2(dif, 0), new Vec2(length, 0), new Vec2(length, edge)}, 3);
                ps2.setAsBox(length / 2, 0.5f);
                ps3.setAsBox(0.5f, length / 2);
                break;
            case 1:
                bd2.position.set(new Vec2(posX + length / 2, posY + 0.5f));
                bd3.position.set(new Vec2(posX + 0.5f, posY + length / 2));

                ps1.set(new Vec2[]{new Vec2(0, 0), new Vec2(edge, 0), new Vec2(0, edge)}, 3);
                ps2.setAsBox(length / 2, 0.5f);
                ps3.setAsBox(0.5f, length / 2);
                break;
            case 2:
                bd2.position.set(new Vec2(posX + length / 2, posY + length - 0.5f));
                bd3.position.set(new Vec2(posX + 0.5f, posY + length / 2));

                ps1.set(new Vec2[]{new Vec2(0, dif), new Vec2(edge, length), new Vec2(0, length)}, 3);
                ps2.setAsBox(length / 2, 0.5f);
                ps3.setAsBox(0.5f, length / 2);
                break;
            default:
                bd2.position.set(new Vec2(posX + length / 2, posY + length - 0.5f));
                bd3.position.set(new Vec2(posX + length - 0.5f, posY + length / 2));

                ps1.set(new Vec2[]{new Vec2(length, dif), new Vec2(length, length), new Vec2(dif, length)}, 3);
                ps2.setAsBox(length / 2, 0.5f);
                ps3.setAsBox(0.5f, length / 2);
        }

        FixtureDef fd1 = new FixtureDef();
        FixtureDef fd2 = new FixtureDef();
        FixtureDef fd3 = new FixtureDef();
        fd1.shape = ps1;
        fd2.shape = ps2;
        fd3.shape = ps3;

        this.body1 = Manager.world.createBody(bd1);
        this.body2 = Manager.world.createBody(bd2);
        this.body3 = Manager.world.createBody(bd3);
        this.body1.createFixture(fd1);
        this.body2.createFixture(fd2);
        this.body3.createFixture(fd3);
    }

    @Override
    public void drawMe(@NotNull Graphics2D g) {
        g.setColor(color);
        Stroke stroke = new BasicStroke(2);
        g.setStroke(stroke);

        int x, y, startAngle;
        int width = Constants.GRID_LENGTH * 2 - 2;
        int height = Constants.GRID_LENGTH * 2 - 2;
        int arcAngle = 90;
        int dotx, doty;

        if (direction == 0) {
            x = this.x - Constants.GRID_LENGTH / 2 * 3 + 1;
            y = this.y - Constants.GRID_LENGTH / 2 + 1;
            startAngle = 0;
            dotx = this.x - Constants.GRID_LENGTH / 2;
            doty = this.y + Constants.GRID_LENGTH / 2 - 2;
        } else if (direction == 1) {
            x = this.x - Constants.GRID_LENGTH / 2 + 1;
            y = this.y - Constants.GRID_LENGTH / 2 + 1;
            startAngle = 90;
            dotx = this.x + Constants.GRID_LENGTH / 2 - 2;
            doty = this.y + Constants.GRID_LENGTH / 2 - 2;
        } else if (direction == 2) {
            x = this.x - Constants.GRID_LENGTH / 2 + 1;
            y = this.y - Constants.GRID_LENGTH / 2 * 3 + 1;
            startAngle = 180;
            dotx = this.x + Constants.GRID_LENGTH / 2 - 2;
            doty = this.y - Constants.GRID_LENGTH / 2;
        } else {
            x = this.x - Constants.GRID_LENGTH / 2 * 3 + 1;
            y = this.y - Constants.GRID_LENGTH / 2 * 3 + 1;
            startAngle = 270;
            dotx = this.x - Constants.GRID_LENGTH / 2;
            doty = this.y - Constants.GRID_LENGTH / 2;
        }

        g.drawArc(x, y, width, height, startAngle, arcAngle);
        g.fillRect(dotx, doty, 2, 2);
    }
}
