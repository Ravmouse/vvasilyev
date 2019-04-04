package ru.job4j.h1io.t4archiveproj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Vitaly Vasilyev, date: 14.03.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Zip {
    /**
     * @param projectName имя zip-архива.
     * @param path директория, которую нужно заархивировать.
     * @param files список файлов.
     * @throws IOException искл.
     */
    public void output(final String projectName, final File path, final List<File> files) throws IOException {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(projectName))) {
            for (File f : files) {
                zout.putNextEntry(new ZipEntry(f.getPath().substring(f.getPath().indexOf(path.getName()))));
                try (InputStream is = new FileInputStream(f)) {
                    int value;
                    while ((value = is.read()) != -1) {
                        zout.write(value);
                    }
                }
                zout.closeEntry();
            }
        }
    }
}