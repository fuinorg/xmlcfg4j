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
import static org.assertj.core.api.StrictAssertions.entry;
import static org.fuin.utils4j.JaxbUtils.unmarshal;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

/**
 * Tests parent/child variable inheritance.
 */
public class ParentChildTest {

    // CHECKSTYLE:OFF

    @Test
    public final void testVariableInheritance() throws Exception {

        // TEST
        final String xml = read("parent-child.xml");
        final ParentElement parent = unmarshal(xml, ParentElement.class,
                ChildElement.class);
        parent.init();

        // VERIFY

        assertThat(parent.getVariables()).containsOnly(
                new Variable("root", "/var/tmp"),
                new Variable("path", "${root}/example"));
        assertThat(parent.getVarMap()).containsOnly(entry("root", "/var/tmp"),
                entry("path", "/var/tmp/example"));
        
        assertThat(parent.getChilds()).hasSize(1);
        
        final ChildElement child = parent.getChilds().get(0);

        assertThat(child.getVariables()).containsOnly(
                new Variable("path", "${root}/example"));
        assertThat(child.getVarMap()).containsOnly(
                entry("root", "/var/tmp"),
                entry("path", "/var/tmp/example/child"));
        
    }

    private static String read(final String resource) {
        try {
            final InputStream in = ParentChildTest.class.getClassLoader()
                    .getResourceAsStream(resource);
            try {
                return IOUtils.toString(in, "utf-8");
            } finally {
                in.close();
            }
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
