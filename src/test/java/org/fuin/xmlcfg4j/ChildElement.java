// CHECKSTYLE:OFF
package org.fuin.xmlcfg4j;

import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "child", namespace = "http://www.fuin.org/xmlcfg4j/test")
public class ChildElement extends AbstractElement {

    public void init(@NotNull Map<String, String> varMap) {
        inheritVariables(varMap);
    }

}
// CHECKSTYLE:ON
