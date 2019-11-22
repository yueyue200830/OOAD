package com.ecnu.ooad;

import com.ecnu.ooad.physics.HinderLeft;
import com.ecnu.ooad.physics.HinderRight;
import com.ecnu.ooad.physics.Substance;
import org.jetbrains.annotations.Contract;

/**
 * @author Jiayi Zhu
 * @date 2019-11-19 14:49
 */
public class GameGrids {
    private Substance[][] grids;
    private boolean hasLeftHinder;
    private boolean hasRightHinder;

    /**
     * This is grid of game. It saves whether a grid has an substance and put a substance into the grid.
     */
    @Contract(pure = true)
    public GameGrids() {
        grids = new Substance[30][30];
        hasLeftHinder = hasRightHinder = false;
    }

    /**
     * Decide whether the grid can add an object.
     * @param x position x.
     * @param y position y.
     * @return True if the grid can add an object.
     */
    public boolean canAddObject(int x, int y) {
        return this.canAddObject(x, y, -1);
    }

    /**
     *  Decide whether the grid can add an object.
     * @param x position x.
     * @param y position y.
     * @param condition The condition of a substance.
     * @return True if the grid can add an object.
     */
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
     * @param substance The object to be added.
     */
    public void addObject(int x, int y, Substance substance) {
        if (grids[x][y] != null) {
            return;
        }
        if (substance instanceof HinderLeft) {
            if (hasLeftHinder) {
                return;
            } else {
                hasLeftHinder = true;
            }
        }
        if (substance instanceof HinderRight) {
            if (hasRightHinder) {
                return;
            } else {
                hasRightHinder = true;
            }
        }

        grids[x][y] = substance;
    }

    /**
     * Get the object in a specific grid.
     * @param x position x.
     * @param y position y.
     * @return Return the object.
     */
    public Substance getObject(int x, int y) {
        return grids[x][y];
    }

    /**
     * Remove the object.
     * @param object The object to be removed.
     */
    public void removeObject(Substance object) {
        for (int i = 0; i < Constants.GAME_HEIGHT / Constants.GRID_LENGTH; i++) {
            for (int j = 0; j < Constants.GAME_WIDTH / Constants.GRID_LENGTH; j++) {
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
