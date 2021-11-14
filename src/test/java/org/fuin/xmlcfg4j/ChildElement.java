// CHECKSTYLE:OFF
package org.fuin.xmlcfg4j;

import static org.fuin.xmlcfg4j.XmlCfg4JTestUtils.NS_TEST;

import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "child", namespace = NS_TEST)
public class ChildElement extends AbstractElement {

    public void init(@NotNull Map<String, String> varMap) {
        inheritVariables(varMap);
    }

}
// CHECKSTYLE:ON
