/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.infinispan.loaders.jdbc.configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * An enumeration of all the recognized XML element local names for the JDBC cache stores
 *
 * @author Tristan Tarrant
 * @since 5.2
 */
public enum Element {
    // must be first
    UNKNOWN(null),

    STRING_KEYED_JDBC_STORE("stringKeyedJdbcStore"),
    BINARY_KEYED_JDBC_STORE("binaryKeyedJdbcStore"),
    MIXED_KEYED_JDBC_STORE("mixedKeyedJdbcStore"),

    CONNECTION_POOL("connectionPool"),
    DATA_SOURCE("dataSource"),
    SIMPLE_CONNECTION("simpleConnection"),

    BINARY_KEYED_TABLE("binaryKeyedTable"),
    STRING_KEYED_TABLE("stringKeyedTable"),

    DATA_COLUMN("dataColumn"),
    ID_COLUMN("idColumn"),
    TIMESTAMP_COLUMN("timestampColumn"),
    ;

    private final String name;

    Element(final String name) {
        this.name = name;
    }

    /**
     * Get the local name of this element.
     *
     * @return the local name
     */
    public String getLocalName() {
        return name;
    }

    private static final Map<String, Element> MAP;

    static {
        final Map<String, Element> map = new HashMap<String, Element>(8);
        for (Element element : values()) {
            final String name = element.getLocalName();
            if (name != null) {
               map.put(name, element);
            }
        }
        MAP = map;
    }

    public static Element forName(final String localName) {
        final Element element = MAP.get(localName);
        return element == null ? UNKNOWN : element;
    }
}
