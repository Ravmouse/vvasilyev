package ru.job4j.h4jdbc.t2jdbcoptimization;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * В этом классе используется XSLT для генерации XML файла другого формата.
 */
public class ConvertXSLT {

    /**
     * @param source файл, который нужно преобразовать.
     * @param dest файл, который получается на выходе.
     * @param scheme файл-схема.
     */
    public void convert(File source, File dest, File scheme) {
        try {
            final TransformerFactory factory = TransformerFactory.newInstance();
            final Transformer transformer = factory.newTransformer(new StreamSource(scheme));
            transformer.transform(new StreamSource(source), new StreamResult(dest));
        } catch (TransformerException te) {
            te.printStackTrace();
        }
    }
}