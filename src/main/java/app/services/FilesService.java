package app.services;

import app.models.FileModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FilesService {
    public Iterable<FileModel> listAllFiles(String path) {
        List<FileModel> files = new ArrayList<>();
        File dir = new File(path);

        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                boolean isFile = true;
                if (file.isDirectory()) {
                    isFile = false;
                }

                files.add(create(file.getName(),
                        file.length(),
                        new Date(file.lastModified()),
                        isFile));
            }
        }

        return files;
    }

    private FileModel create(String name, Long size, Date lastModifiedDate, boolean isFile) {
        return new FileModel(name, size, lastModifiedDate, isFile);
    }
}
