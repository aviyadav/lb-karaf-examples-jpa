
BUILD
--------------------------------------------------------------------------------

gradle clean install


INSTALLATION
--------------------------------------------------------------------------------

Install Karaf's enterprise features:

    features:install transaction
    features:install jndi
    features:install jpa


Install bundles:
    karaf@root> install -s mvn:org.hsqldb/hsqldb/2.2.9
    install -s mvn:org.hsqldb/hsqldb/2.2.9
    install -s mvn:com.fasterxml/classmate/0.5.4
    install -s mvn:org.apache.geronimo.specs/geronimo-servlet_3.0_spec/1.0
    install -s mvn:org.apache.geronimo.specs/geronimo-validation_1.0_spec/1.1
    install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.antlr/2.7.7_5
    install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.ant/1.8.2_2
    install -s mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.dom4j/1.6.1_5
    install -s mvn:org.hibernate/hibernate-validator/4.2.0.Final
    install -s mvn:org.jboss.javassist/com.springsource.javassist/3.15.0.GA
    install -s mvn:org.jboss.spec.javax.security.jacc/jboss-jacc-api_1.4_spec/1.0.2.Final
    install -s wrap:mvn:org.jboss/jandex/1.1.0.Alpha1
    install -s mvn:org.jboss.logging/jboss-logging/3.1.0.GA

    install -s wrap:mvn:http://snapshots.jboss.org/maven2\!org.hibernate.common/hibernate-commons-annotations/4.0.1-SNAPSHOT
    install -s mvn:http://snapshots.jboss.org/maven2\!org.hibernate/hibernate-core/4.2.0.CR2
    install -s mvn:http://snapshots.jboss.org/maven2\!org.hibernate/hibernate-entitymanager/4.2.0.CR2
    install -s mvn:http://snapshots.jboss.org/maven2\!org.hibernate/hibernate-osgi/4.2.0.CR2

Install the example bundle:
    cp datasources/jpa-datasources.xml $KARAF_HOME/deploy

    install -s mvn:karaf-examples/jpa-hibernate/1.0.0.SNAPSHOT


TEST
--------------------------------------------------------------------------------

    item:hibernate-am-add name1 descr1
    item:hibernate-am-add name2 descr2
    item:hibernate-am-list
