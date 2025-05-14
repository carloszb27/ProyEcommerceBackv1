package com.proyectos.ProyectoEcommerce.util.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

    public static void write(String text, String path) throws IOException {
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
}
