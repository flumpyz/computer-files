package app.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileModel {
    private final String name;
    private final Long size;
    private final Date lastModifiedDate;
    private final boolean isFile;
    private final String formatLastModifiedDate;

    public FileModel(String name, Long size, Date lastModifiedDate, boolean isFile) {
        this.name = name;
        this.size = size / 8;
        this.lastModifiedDate = lastModifiedDate;
        this.isFile = isFile;
        this.formatLastModifiedDate = new SimpleDateFormat("dd/MM/yyyy , HH:mm:ss").format(lastModifiedDate);
    }

    public String getName() {
        return name;
    }

    public Long getSize() {
        return size;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public boolean getIsFile() {
        return isFile;
    }

    public String getFormatLastModifiedDate() {
        return formatLastModifiedDate;
    }
}
