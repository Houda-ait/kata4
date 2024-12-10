package software.ulpgc.control;

import software.ulpgc.model.Title;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TsvTitleReader implements TitleReader {
    private final File file;

    public TsvTitleReader(File file) {
        this.file = file;
    }

    @Override
    public Iterator<Title> read() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            return readWith(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Iterator<Title> readWith(BufferedReader reader) throws IOException {
        TsvTitleDeserializer deserializer = new TsvTitleDeserializer();
        List<Title> result = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            Title title = deserializer.deserialize(line);
            result.add(title);
        }

        return result.iterator();
    }
}
