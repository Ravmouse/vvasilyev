package ru.job4j.h4jdbc.t2jdbcoptimization;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * В этом классе используется SAX парсинг для считывания данных из XML файла.
 */
public class SAXFieldCount extends DefaultHandler {
    /**
     * Для подсчета суммы всех значений.
     */
    private int sum;

    /**
     * @param uri .
     * @param localName .
     * @param qName .
     * @param attributes .
     * @throws SAXException .
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String name = attributes.getValue("field");
        if (name != null && !name.isEmpty()) {
            sum += Integer.parseInt(name);
        }
    }

    /**
     * @throws SAXException .
     */
    @Override
    public void endDocument() throws SAXException {
        System.out.println(sum);
    }

    /**
     * @param args .
     * @throws SQLException .
     */
    public static void main(String[] args) throws SQLException {
        long start = System.currentTimeMillis();
        final List<Integer> list;
        try (final StoreSQL sql = new StoreSQL()) {
            sql.generate(10);
            list = sql.getAllDataFromDB();
        }

        List<StoreXML.Entry> entryList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            entryList.add(new StoreXML.Entry(list.get(i)));
        }
        String path = SAXFieldCount.class.getResource("scheme.xml").toExternalForm();
        path = path.substring(path.indexOf("C"), path.lastIndexOf("/") + 1);
        StoreXML xml = new StoreXML(path + "file.xml");
        xml.save(new StoreXML.Entries(entryList));

        new ConvertXSLT().convert(new File(path + "file.xml"), new File(path + "fileXSLT.xml"), new File(path + "scheme.xml"));

        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(new File(path + "fileXSLT.xml"), new SAXFieldCount());
            System.out.println(System.currentTimeMillis() - start);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}

//1000 - 7 sec
//10000 - 65 sec
//10000 - 1,3 sec w/ autocommit = false;
//100000 - 2,2 sec w/ autocommit = false;
//1000000 - 16 sec w/ autocommit = false;