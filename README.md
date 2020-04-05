# Spring Boot On Weblogic
 A Spring Boot 2.x REST App which runs on Weblogic 12c or Weblogic 14c
 
 Running Spring Boot Application on Weblogic 14c and Weblogic 12c

If you are here, then that means you are already familiar with benefits of Spring Boot and you want to leverage these benefits in your next Application which will be deployed on Weblogic Server.

So without wasting much let’s get started with how to do that.

First thing we will do is that, when we run the spring initializer we will select the default packaging mode as WAR. I have used spring website because I am using IntelliJ Community Edition. You can use STS or intelliJ Ultimate Edition for the same.

![](readme-images/spring-init)

Once the skeleton project is created you will see a servlet initializer config class created for you.
![](readme-images/ServletInitializer)
 
This class extends the SpringBootServletInitializer class and overrides the configure method. All the codes are auto generated for you and digging into the details of this class is indeed a concern for another topic.

Now we will have to add two xml files inside folder: main -> webapp -> WEB-INF. Spring creates resources folder for you but you will have to manually add webapp folder adjacent to resources and WEB-INF goes inside webapp folder.

Let’s create our two xml files inside WEB-INF folder:
1)	weblogic.xml
2)	disparcherServlet-servlet.xml

We will just add the root bean tag in the dispatcher servlet xml file and will leave it empty.

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd">

</beans>
```

The main part is the weblogic.xml. We will define the context root path here and the most important part prefer-application-packages tag:

```
<?xml version="1.0" encoding="UTF-8"?>
<weblogic-web-app xmlns="http://xmlns.oracle.com/weblogic/weblogic-web-app"
                  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                  xsi:schemaLocation="http://xmlns.oracle.com/weblogic/weblogic-web-app
                  http://xmlns.oracle.com/weblogic/weblogic-web-app/1.7/weblogic-web-app.xsd">

    <context-root>/narif/poc/echo</context-root>

    <session-descriptor>
        <cookies-enabled>false</cookies-enabled>
    </session-descriptor>

    <container-descriptor>
        <prefer-application-packages>
            <package-name>org.slf4j</package-name>
            <package-name>com.fasterxml</package-name>
            <package-name>org.springframework</package-name>
            <package-name>org.apache.logging</package-name>
            <package-name>org.apache</package-name>
        </prefer-application-packages>
    </container-descriptor>
</weblogic-web-app>
```


These are the bare minimum stuffs you will need to deploy a spring boot war on weblogic. After completing the above steps you can do a dry run. 

So run mvn package and deploy the war file on weblogic.

Build phase:
 ![](readme-images/build)
 
Deployment phase:
 ![](readme-images/deploy)
 
Now let’s create a very simple ping service which will respond with “pong”.

```
package narif.poc.wlseries.echorestapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoRestService {

    @GetMapping("ping")
    public String ping(){
        return "pong";
    }
}
```
 
Again build and ship to wls and try this url in postman.
 ![](readme-images/postman)
 
•	NOTE:
•	I have used Spring Boot 2.2.6
•	I have tested on Weblogic 12c and Weblogic 14c.

