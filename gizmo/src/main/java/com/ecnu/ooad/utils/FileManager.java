package com.ecnu.ooad.utils;

import java.io.*;

/**
 * @author Yiqing Tao
 * @date 2019-11-19 12:25
 */
public class FileManager {

    public FileManager() {

    }
    public static String readGame(String fileName){
        String line;
        try {
            FileManager fake = new FileManager();
            File file = new File("E:/OOAD/OOAD/OOAD/gizmo/" + fileName);
            InputStream is = new FileInputStream(file);
            Reader reader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(reader);
            line = bufferedReader.readLine();
            bufferedReader.close();
            reader.close();
            is.close();
            return line;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void saveGame(String gameConfig) {
        try {
            File file = new File("game");
            Writer writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            writer.write(gameConfig);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
