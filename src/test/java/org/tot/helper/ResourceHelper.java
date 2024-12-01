package org.tot.helper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class ResourceHelper {

    public static Path findFile(String name) {
        var resourceURL = ResourceHelper.class.getClassLoader().getResource(name);

        try {
            return Path.of(Objects.requireNonNull(resourceURL).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> loadLinesFromFile(String path) {
        var file = findFile(path);
        try {
            return Files.readAllLines(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
