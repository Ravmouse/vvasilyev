package ru.job4j.h1io.t3scanfilesys;

import org.junit.Test;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Rav, date: 03.03.2019
 * @version 1.0
 */
public class SearchTest {
    /**
     *
     */
    @Test
    public void whenFoundExtsThenShowFilenames() {
        String parent = String.format("%sJavaSearch", System.getProperty("java.io.tmpdir"));
        List<String> exts = Arrays.asList("java", "txt");
        List<File> fileList = Search.files(parent, exts);
        assertThat(fileList.get(0).getName(), is("comic.txt"));
        assertThat(fileList.get(1).getName(), is("jvm.java"));
        assertThat(fileList.get(2).getName(), is("novel.txt"));
        assertThat(fileList.get(3).getName(), is("program.java"));
        assertThat(fileList.get(4).getName(), is("server.java"));
        assertThat(fileList.get(5).getName(), is("text.txt"));
    }
}