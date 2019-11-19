package com.ecnu.ooad.physics;

import com.ecnu.ooad.Constants;
import com.ecnu.ooad.Manager;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import java.awt.*;

/**
 * @author Yiqing Tao
 * @date 2019-11-16 10:55
 */
public class CurveTrack extends Track {
    private int x;
    private int y;
    private Body body;
    private Color color = Color.yellow;
    private int direction;

    public CurveTrack(float x, float y, int direction) {
        this.x = (int) x;
        this.y = (int) y;
        this.direction = direction;
        this.initCurveTrack();
    }

    private void initCurveTrack() {
        BodyDef bd = new BodyDef();
        bd.position.set(new Vec2(x - Constants.GRID_LENGTH / 2, y - Constants.GRID_LENGTH / 2));
        bd.bullet = true;
        bd.type = BodyType.STATIC;

        float edge = (float) ((Math.sqrt(2f) - 1f) / 2f / Math.sqrt(2f) * (double) Constants.GRID_LENGTH);
        System.out.println(edge);
        float length = Constants.GRID_LENGTH;
        float dif = length - edge;

        PolygonShape ps = new PolygonShape();
        switch (direction) {
            case 0:
                ps.set(new Vec2[]{new Vec2(0, 0), new Vec2(length, 0), new Vec2(length, length),
                        new Vec2(length-1, length), new Vec2(length-1, edge+1), new Vec2(length-1-edge, 1),
                        new Vec2(0, 1)}, 7);
                break;
            case 1:
                ps.set(new Vec2[]{new Vec2(0, 0), new Vec2(length, 0), new Vec2(length, 1),
                        new Vec2(edge+1, 1), new Vec2(1, edge+1), new Vec2(1, length),
                        new Vec2(0, length)}, 7);
                break;
            case 2:
                ps.set(new Vec2[]{new Vec2(0, 0), new Vec2(0.5f, 0), new Vec2(0.5f, length-edge-1),
                        new Vec2(edge+1, length-1), new Vec2(length, length-1), new Vec2(length, length),
                        new Vec2(0, length)}, 7);
                break;
            default:
                ps.set(new Vec2[]{new Vec2(length, dif), new Vec2(length, length), new Vec2(dif, length)}, 3);
        }

        FixtureDef fd = new FixtureDef();
        fd.shape = ps;

        this.body = Manager.world.createBody(bd);
        this.body.createFixture(fd);
    }

    @Override
    public void drawMe(Graphics2D g) {
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
