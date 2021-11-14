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

import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.NoFieldShadowingRule;
import com.openpojo.validation.rule.impl.NoPublicFieldsRule;
import com.openpojo.validation.test.impl.DefaultValuesNullTester;
import com.openpojo.validation.test.impl.GetterTester;

/**
 * Basic functions for all tests.
 */
public final class XmlCfg4JTestUtils {

    public static final String NS_CFG4J = "http://www.fuin.org/xmlcfg4j/0.2.1";

    public static final String NS_TEST = "http://www.fuin.org/xmlcfg4j-test";
    
    /**
     * Private utility class constructor.
     */
    private XmlCfg4JTestUtils() {
    }

    /**
     * Creates a configured POJO validator.
     * 
     * @return New instance.
     */
    public static Validator createPojoValidator() {

        final ValidatorBuilder pv = ValidatorBuilder.create();

        pv.with(new NoPublicFieldsRule());
        pv.with(new NoFieldShadowingRule());

        pv.with(new DefaultValuesNullTester());
        pv.with(new GetterTester());

        return pv.build();
    }

}
