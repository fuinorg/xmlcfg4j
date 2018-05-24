// CHECKSTYLE:OFF
package org.fuin.xmlcfg4j;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "parent", namespace = "http://www.fuin.org/xmlcfg4j/test")
public class ParentElement extends AbstractElement {

    @XmlElement(name = "child", namespace = "http://www.fuin.org/xmlcfg4j/test")
    private List<ChildElement> childs;

    public List<ChildElement> getChilds() {
        return Collections.unmodifiableList(childs);
    }

    public void init() {

        // No parent to inherit from
        inheritVariables(null);

        // Initialize childs
        if (childs != null) {
            for (ChildElement child : childs) {
                child.init(getVarMap());
            }
        }

    }

}
// CHECKSTYLE:ON
