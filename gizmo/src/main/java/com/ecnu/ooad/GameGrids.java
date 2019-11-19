package com.ecnu.ooad;

import com.ecnu.ooad.physics.HinderLeft;
import com.ecnu.ooad.physics.HinderRight;
import org.jetbrains.annotations.Contract;

/**
 * @author Jiayi Zhu
 * @date 2019-11-19 14:49
 */
public class GameGrids {
    private Object[][] grids;
    private boolean hasLeftHinder;
    private boolean hasRightHinder;

    @Contract(pure = true)
    public GameGrids() {
        grids = new Object[30][30];
        hasLeftHinder = hasRightHinder = false;
    }

    public boolean hasObject(int x, int y) {
        return grids[x][y] != null;
    }

    public boolean addObject(int x, int y, Object object) {
        if (hasObject(x, y)) {
            return false;
        }
        if (object instanceof HinderLeft) {
            if (hasLeftHinder) {
                return false;
            } else {
                hasLeftHinder = true;
            }
        }
        if (object instanceof HinderRight) {
            if (hasRightHinder) {
                return false;
            } else {
                hasRightHinder = true;
            }
        }

        grids[x][y] = object;
        return true;
    }

    public Object getObject(int x, int y) {
        return grids[x][y];
    }

}
