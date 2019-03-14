package camel.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Jopa on 11/1/2017.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SubTree {
    @XmlElement(name="key")
    String name;
    @XmlElement(name="value")
    String value;

    public SubTree() {}

    public SubTree(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public void setName(String name) {this.name = name;}
    public String getName() {return name;}
    public void setValue(String value) {this.value = value;}
    public String getValue() {return value;}

    @Override
    public String toString() {return "SubTree{" +
            "name='" + name + '\'' +
            ", value='" + value + '\'' +
            '}';
    }
}
