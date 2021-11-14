package org.fuin.xmlcfg4j;

/**
 * Defines the namespace. 
 */
public final class XmlCfg4JNamespace {

    /** Namespace URL. */
    public static final String NAMESPACE = "http://www.fuin.org/xmlcfg4j/0.2.1";
    
    /** Namespace prefix. */
    public static final String NS_PREFIX = "cfg4j";
    
    private XmlCfg4JNamespace() {
        throw new UnsupportedOperationException("It's not allowed to create an instance of this utility class");
    }
    
}
