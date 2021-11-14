// CHECKSTYLE:OFF
package org.fuin.xmlcfg4j;


import static org.fuin.xmlcfg4j.XmlCfg4JTestUtils.NS_TEST;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "parent", namespace = NS_TEST)
public class ParentElement extends AbstractElement {

    @XmlElement(name = "child", namespace = NS_TEST)
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
