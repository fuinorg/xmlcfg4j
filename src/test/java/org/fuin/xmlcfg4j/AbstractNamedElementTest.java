/**
 * Copyright (C) 2015 Michael Schnell. All rights reserved. 
 * http://www.fuin.org/
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library. If not, see http://www.gnu.org/licenses/.
 */
package org.fuin.xmlcfg4j;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.fuin.utils4j.JaxbUtils.XML_PREFIX;
import static org.fuin.utils4j.JaxbUtils.marshal;
import static org.fuin.utils4j.JaxbUtils.unmarshal;
import static org.fuin.xmlcfg4j.XmlCfg4JTestUtils.createPojoValidator;

import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlRootElement;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;

/**
 * Tests for {@link AbstractNamedElement}.
 */
public class AbstractNamedElementTest {

    // CHECKSTYLE:OFF

    @Test
    public final void testPojoStructureAndBehavior() {

        final PojoClass pc = PojoClassFactory.getPojoClass(MyElement.class);
        final Validator pv = createPojoValidator();
        pv.validate(pc);

    }

    @Test
    public final void testMarshal() throws Exception {

        // PREPARE
        final MyElement testee = new MyElement("NAME");

        // TEST
        final String result = marshal(testee, MyElement.class);

        // VERIFY
        assertThat(result).isEqualTo(XML_PREFIX + "<ns2:my-named-element name=\"NAME\" xmlns=\"http://www.fuin.org/xmlcfg4j\""
                + " xmlns:ns2=\"http://www.fuin.org/xmlcfg4j-test\"/>");

    }

    @Test
    public final void testMarshalVariables() throws Exception {

        // PREPARE
        final MyElement testee = new MyElement("NAME");
        testee.addVariable(new Variable("a", "1"));

        // TEST
        final String result = marshal(testee, MyElement.class);

        // VERIFY
        assertThat(result).isEqualTo(XML_PREFIX + "<ns2:my-named-element name=\"NAME\" xmlns=\"http://www.fuin.org/xmlcfg4j\""
                + " xmlns:ns2=\"http://www.fuin.org/xmlcfg4j-test\">" + "<variable name=\"a\" value=\"1\"/>" + "</ns2:my-named-element>");

    }

    @Test
    public final void testUnmarshal() throws Exception {

        // TEST
        final MyElement testee = unmarshal("<ns2:my-named-element name=\"NAME\" xmlns=\"http://www.fuin.org/xmlcfg4j\""
                + " xmlns:ns2=\"http://www.fuin.org/xmlcfg4j-test\"/>", MyElement.class);

        // VERIFY
        assertThat(testee).isNotNull();
        assertThat(testee.getVariables()).isNull();
        assertThat(testee.getVarMap()).hasSize(0);

    }

    @Test
    public final void testUnmarshalVariables() throws Exception {

        // TEST
        final MyElement testee = unmarshal("<ns2:my-named-element name=\"NAME\" xmlns=\"http://www.fuin.org/xmlcfg4j\""
                + " xmlns:ns2=\"http://www.fuin.org/xmlcfg4j-test\">" + "<variable value=\"1\" name=\"a\"/>" + "</ns2:my-named-element>",
                MyElement.class);
        testee.inheritVariables(null);

        // VERIFY
        assertThat(testee).isNotNull();
        assertThat(testee.getVariables()).containsExactly(new Variable("a", "1"));
        assertThat(testee.getVarMap()).containsOnly(entry("a", "1"));

    }

    @Test
    public final void testHashCodeEquals() {
        EqualsVerifier.forClass(MyElement.class).withRedefinedSuperclass().withIgnoredFields("variables").suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }

    /**
     * Test class.
     */
    @XmlRootElement(name = "my-named-element", namespace = "http://www.fuin.org/xmlcfg4j-test")
    private static final class MyElement extends AbstractNamedElement {

        @SuppressWarnings("unused")
        public MyElement() {
            super();
        }

        public MyElement(@NotEmpty String name) {
            super(name);
        }

    }

}
