package com.proyectos.ProyectoEcommerce.util.file;

import java.io.*;

public class FileUtil implements Serializable {

    private static FileUtil instance = null;

    private FileUtil(){
    }

    public static FileUtil getInstance() {
        if(instance == null) {
            synchronized (FileUtil.class){
                if(instance == null) {
                    instance = new FileUtil();
                }
            }
        }
        return instance;
    }

    public void write(String text, String path) throws IOException {
        File file = new File(path);
        if(!file.exists()) {
            boolean created = file.createNewFile();

            if(!created) {
                throw new RuntimeException("No se pudo crear un archivo");

            }
        }
        FileWriter fw = new FileWriter(file, true);

        try(fw; BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(text);
            bw.newLine();
        }
    }

    protected Object readResolver(){
        return instance;
    }
}
