package home.denis.task1.io;

import home.denis.task1.Figure;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FigureFileWriter {
    public static void writeFigureToFile(String filename, Figure figure, boolean append) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename,append), StandardCharsets.UTF_8)
        )) {
            writer.write(figure.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
