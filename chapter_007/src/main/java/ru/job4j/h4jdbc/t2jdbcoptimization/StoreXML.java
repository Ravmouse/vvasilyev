package ru.job4j.h4jdbc.t2jdbcoptimization;

import java.io.File;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * В этом классе осуществляется конвертация Java объекта в XML файл.
 */
public class StoreXML {
    /**
     * Ссылка на объект для сохранения XML файла.
     */
    private final File file;
    /**
     * Для конвертации объекта в XML файл.
     */
    private Marshaller marshaller;

    /**
     * @param target путь до XML файла.
     */
    public StoreXML(String target) {
        file = new File(target);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Класс, в котором имеется список Entry.
     */
    @XmlRootElement
    public static class Entries {
        /**
         * Список Entry.
         */
        private List<Entry> entry;

        /**
         * Конструктор.
         */
        public Entries() {
        }

        /**
         * @param entry ссылка на список Entry.
         */
        public Entries(List<Entry> entry) {
            this.entry = entry;
        }

        /**
         * @return список Entry.
         */
        public List<Entry> getEntry() {
            return entry;
        }

        /**
         * @param entry список для присвоения.
         */
        public void setEntry(List<Entry> entry) {
            this.entry = entry;
        }

    }

    /**
     * Класс, в котором есть ссылка на Field.
     */
    @XmlRootElement
    public static class Entry {
        /**
         * Ссылка на Field.
         */
        private Field field;

        /**
         * Конструктор.
         */
        public Entry() {
        }

        /**
         * @param value целочисленное значение.
         */
        public Entry(int value) {
            field = new Field(value);
        }

        /**
         * @return ссылку на объект типа Field.
         */
        public Field getField() {
            return field;
        }

        /**
         * @param field ссылка для присвоения.
         */
        @XmlElement
        public void setField(Field field) {
            this.field = field;
        }
    }

    /**
     * Класс с целочисленным значением.
     */
    @XmlRootElement
    public static class Field {
        /**
         * Целое число.
         */
        private int value;

        /**
         * Конструктор.
         */
        public Field() {
        }

        /**
         * @param value целое число.
         */
        public Field(int value) {
            this.value = value;
        }

        /**
         * @return целое число.
         */
        public int getValue() {
            return value;
        }

        /**
         * @param value целое число для присвоения.
         */
        @XmlElement
        public void setValue(int value) {
            this.value = value;
        }
    }

    /**
     * @param entries ссылка на объект типа Entries.
     */
    public void save(Entries entries) {
        try {
            marshaller.marshal(entries, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}