package com.ecnu.ooad.view;

import com.ecnu.ooad.Controller;
import com.ecnu.ooad.Manager;
import com.ecnu.ooad.utils.TransformUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

/**
 * @author Yiqing Tao
 * @date 2019-11-20 19:31
 */
public class MenuListener implements ActionListener {
    private Controller controller;
    private TransformUtil transformUtil;

    public MenuListener(Controller controller) {
        this.controller = controller;
        this.transformUtil = new TransformUtil(controller);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if("New Game".equals(command)) {
            System.out.println("new game");
        }else if("Save Game".equals(command)) {
            System.out.println("save game");
            String gameConfig = this.transformUtil.objectToJson(this.controller.getObjectList());
            com.ecnu.ooad.utils.FileManager.saveGame(gameConfig);
        }else if("Load Game".equals(command)) {
            System.out.println("load game");
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jfc.showDialog(new JLabel(), "choose file");
            File file = jfc.getSelectedFile();
            if(file.isFile()) {
                System.out.println("choose a file");
                System.out.println(file.getName());
                String gameConfig = com.ecnu.ooad.utils.FileManager.readGame(file.getName());
                List<Object> objectList = this.transformUtil.jsonToObject(gameConfig);
            }else {
                System.out.println("not a file");
            }

        }
    }
}
