package ru.job4j.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private boolean sedan;
    @XmlAttribute
    private int size;
    @XmlAttribute
    private String nameFactory;
    @XmlElement
    private Contact contact;
    @XmlAttribute
    private String[] model;

    public Car() {
    }

    public Car(boolean sedan, int size, String nameFactory, Contact contact, String[] model) {
        this.sedan = sedan;
        this.size = size;
        this.nameFactory = nameFactory;
        this.contact = contact;
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car{"
                + "sedan=" + sedan
                + ", size=" + size
                + ", nameFactory='" + nameFactory + '\''
                + ", contact=" + contact
                + ", model=" + Arrays.toString(model)
                + '}';
    }

    public static void main(String[] args) throws Exception {
        final Car car = new Car(true, 5, "BMW",
                new Contact(1, "12345"), new String[]{"Z", "330"});
        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Car result = (Car) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}