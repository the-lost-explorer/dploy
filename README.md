#dploy

This is a simple utility which will be made into a java app to run GWT applications on Tomcat server. It creates **.war** file using Apache Ant and then deploys it on Apache Tomcat Server.

**Update v1.0**
Java Applet for deploying your gwt app on Windows system is now ready.

**v0.1**
As of now one can use the batch file as follows:

1. Open Commant Prompt in the folder you save the batch file in.
2. Type following: 

```
  set PROJECT_HOME={project home directory}
  set PROJECT_NAME={project name(check your build.xml)}
```
3. Start the batch file: 

  ```dploy.bat```
 
 **Dependencies:**
 
 1. Apache Tomcat 
 2. Apache Ant
 3. JDK
 
 
