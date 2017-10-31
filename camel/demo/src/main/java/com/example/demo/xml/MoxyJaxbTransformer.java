package com.example.demo.xml;

import com.example.demo.domain.Book;
import org.eclipse.persistence.jaxb.JAXBContextProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by Jopa on 10/31/2017.
 */
@Component
public class MoxyJaxbTransformer<T extends Book> {
    private Class<Book> beanClazz = Book.class;

    @Bean
    public JAXBContext newJaxbContext() throws JAXBException {
        HashMap<String, Object> props = new HashMap<>();
        props.put(JAXBContextProperties.OXM_METADATA_SOURCE, "xml-bindings.xml");
        return JAXBContext.newInstance(new Class[] {beanClazz}, props);
    }

    public Book unmarshall(InputStream xml) throws JAXBException {
        HashMap<String, Object> props = new HashMap<>();
        props.put(JAXBContextProperties.OXM_METADATA_SOURCE, "xml-bindings.xml");
        JAXBContext jc = JAXBContext.newInstance(new Class[] {beanClazz}, props);
        StreamSource xmlSource = new StreamSource(xml);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        JAXBElement<T> je1 = unmarshaller.unmarshal(xmlSource, (Class<T>) beanClazz);
        return je1.getValue();
    }
}
