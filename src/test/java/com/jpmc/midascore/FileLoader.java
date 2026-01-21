package com.jpmc.midascore;

import org.springframework.stereotype.Component;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;

import java.io.InputStream;

@Component
public class FileLoader {

    public String[] loadStrings(String path) {
        try {
            InputStream inputStream =
                    this.getClass().getClassLoader()
                            .getResourceAsStream(path.startsWith("/") ? path.substring(1) : path);

            String fileText = IOUtils.toString(inputStream, "UTF-8");
            return fileText.split(System.lineSeparator());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}