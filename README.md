xmlcfg4j
========

XML based configuration for Java.

[![Build Status](https://fuin-org.ci.cloudbees.com/job/xmlcfg4j/badge/icon)](https://fuin-org.ci.cloudbees.com/job/xmlcfg4j/)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.fuin/xmlcfg4j/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.fuin/xmlcfg4j/)
[![LGPLv3 License](http://img.shields.io/badge/license-LGPLv3-blue.svg)](https://www.gnu.org/licenses/lgpl.html)
[![Java Development Kit 1.8](https://img.shields.io/badge/JDK-1.8-green.svg)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

Description
===========

This library provides base classes for JAXB based configurations. The configuration itself is always implemented in another (specific) project.

Variable
--------
A variable tag defines simply a key and a value. 
```XML
<variable name="abc" value="def" />
```
The tag may reference other variables. 
```XML
<variable name="a" value="1" />
<variable name="b" value="${a}/2" />
<variable name="c" value="${b}/3" />
```
The final values of the variables are: a="1" / b="1/2" / c="1/2/3".

AbstractElement
---------------
The class [AbstractElement](https://raw.github.com/fuinorg/xmlcfg4j/master/src/main/java/org/fuin/xmlcfg4j/AbstractElement.java) is mainly a container for variables.

You will need to extend it in your project that has a JAXB based XML configuration: 
```Java
@XmlRootElement(name = "my-element")
public class MyElement extends AbstractElement {
}
```
Then you can use it like this:
```XML
<my-element>
    <variable name="a" value="1" />
    <variable name="b" value="${a}/2" />
</my-element>
```

AbstractNamedElement
--------------------
The class [AbstractNamedElement](https://raw.github.com/fuinorg/xmlcfg4j/master/src/main/java/org/fuin/xmlcfg4j/AbstractNamedElement.java) adds only a unique name to the [AbstractElement](https://raw.github.com/fuinorg/xmlcfg4j/master/src/main/java/org/fuin/xmlcfg4j/AbstractElement.java).

You will need to extend it in your project that has a JAXB based XML configuration: 
```Java
@XmlRootElement(name = "my-named-element")
public class MyNamedElement extends AbstractNamedElement {
}
```
Then you can use it like this:
```XML
<my-named-element name="AnyName">
    <variable name="a" value="1" />
    <variable name="b" value="${a}/2" />
</my-named-element>
```

Inherit and override variables
------------------------------
If you create elements that contain other elements, it's easy to support inheritance of variables and also override them.

```XML
<?xml version="1.0" encoding="UTF-8"?>
<test:parent xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://www.fuin.org/xmlcfg4j" xmlns:test="http://www.fuin.org/xmlcfg4j/test">

	<variable name="root" value="/var/tmp" />
	<variable name="path" value="${root}/example" />
	<!-- Read a file from the classpath and replace variables contained in it. -->
	<variable name="res" url="classpath:header.txt" encoding="utf-8" />
	<!-- Use escaped characters. -->
	<variable name="escapes" value="\r\n\t" />

	<test:child>
		<!-- Inherits variable "root" -->
		<!-- Inherits variable "res" -->
		<!-- Inherits variable "escapes" -->
		<!-- Overrides variable "path" = "/var/tmp/example/child" -->
		<variable name="path" value="${path}/child" />
	</test:child>

</test:parent>
```
See [ParentChildTest](https://raw.github.com/fuinorg/xmlcfg4j/master/src/test/java/org/fuin/xmlcfg4j/ParentChildTest.java) for an example on how to use it.

-----------------------------------------------------
Building this project
=====================

Checkout the source code from the repository and use the following commands inside the project's directory to build the project.

Linux:
```
$ ./mvnw clean install
```

Windows:
```
$ mvnw.cmd clean install
```

-----------------------------------------------------

Snapshots
=========

Snapshots can be found on the [OSS Sonatype Snapshots Repository](http://oss.sonatype.org/content/repositories/snapshots/org/fuin "Snapshot Repository"). 

Add the following to your [.m2/settings.xml](http://maven.apache.org/ref/3.2.1/maven-settings/settings.html "Reference configuration") to enable snapshots in your Maven build:

```xml
<repository>
    <id>sonatype.oss.snapshots</id>
    <name>Sonatype OSS Snapshot Repository</name>
    <url>http://oss.sonatype.org/content/repositories/snapshots</url>
    <releases>
        <enabled>false</enabled>
    </releases>
    <snapshots>
        <enabled>true</enabled>
    </snapshots>
</repository>
```
