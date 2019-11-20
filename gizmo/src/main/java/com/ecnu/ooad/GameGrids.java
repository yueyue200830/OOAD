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

    public boolean canAddObject(int x, int y, int condition) {
        boolean canAdd = grids[x][y] == null;
        if (condition == 8 && hasLeftHinder) {
            canAdd = false;
        }
        if (condition == 9 && hasRightHinder) {
            canAdd = false;
        }
        return canAdd;
    }

    /**
     * Add the object to the grid x, y.
     * @param x Position X.
     * @param y Position Y.
     * @param object The object to be added.
     */
    public void addObject(int x, int y, Object object) {
        if (grids[x][y] != null) {
            return;
        }
        if (object instanceof HinderLeft) {
            if (hasLeftHinder) {
                return;
            } else {
                hasLeftHinder = true;
            }
        }
        if (object instanceof HinderRight) {
            if (hasRightHinder) {
                return;
            } else {
                hasRightHinder = true;
            }
        }

        grids[x][y] = object;
    }

    public Object getObject(int x, int y) {
        return grids[x][y];
    }

    public void removeObject(Object object) {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (this.grids[i][j] == object) {
                    this.grids[i][j] = null;
                }
            }
        }
        if (object instanceof HinderLeft) {
            this.hasLeftHinder = false;
        } else if (object instanceof HinderRight) {
            this.hasRightHinder = false;
        }
    }

}
