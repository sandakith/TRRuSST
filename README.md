TRRuSST (Trust and Review based Recommendation tool-sUite for Service Selection Testing)
========================================================================================

TRRuSST is a project for evaluating trustworthiness of online software services
by evaluating reviews and recommends services based on trust.

## System Requirements

* [JAVA 6](http://www.java.com/en/download/faq/java_6.xml)
* [Maven 3.2.x](http://maven.apache.org/download.cgi) 
* [Python 2.x](http://www.python.org/download/releases/2.7.2)


Environment Setup
-------------------

Set ```TRRuSST_ROOT``` environment variable to the location of the project. 
Next, clone the TRRuSST repo and configure the environment variables:

**Windows**

    set TRRuSST_ROOT=[location of TRRuSST]
    cd %TRRuSST_ROOT%\scripts\windows
	env.bat

**Unix**

    export TRRuSST_ROOT=[location of TRRuSST]
    cd $TRRuSST_ROOT/scripts/unix
	sh env.sh

Quick Build
----------------

To build the TRRuSST workspace: 

    cd $TRRuSST_ROOT
    mvn clean install


License
----------------

GNU GENERAL PUBLIC LICENSE
    Version 3, 29 June 2007
    http://choosealicense.com/licenses/gpl-3.0/

 
