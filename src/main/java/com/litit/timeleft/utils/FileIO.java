package com.litit.timeleft.utils;

import java.io.*;

public class FileIO {

    private static final File file = new File("src/main/resources/com/litit/timeleft/date.txt");

    public static String read() {
        String date = "";
        try {
            if (!file.exists()) {
                write(date);
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            date = bufferedReader.readLine();
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return date;
    }

    public static void write(String s) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(s);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

