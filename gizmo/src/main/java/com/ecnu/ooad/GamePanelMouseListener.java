package com.ecnu.ooad;

import com.ecnu.ooad.physics.Ball;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanelMouseListener implements MouseListener {
        private Manager manager;

        public GamePanelMouseListener(Manager manager) {
            this.manager = manager;
            System.out.println("new listener");
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("mouse clicked");
            if(MouseEvent.BUTTON1 == e.getButton()) {
                int condition = this.manager.getIngredientCondition();
                System.out.println(condition);
                switch(condition){
                    case 0:
                        break;
                    case 1:
                        this.addBall(e);
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    default:
                        break;


                }

            }
        }

        public void addBall(MouseEvent e) {
            System.out.println("add ball");
            int x = e.getX();
            int y = e.getY();
            Ball newBall = new Ball(x,y);
            manager.addBall(newBall);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("mouse pressed");
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
}
