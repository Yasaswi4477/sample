package me.ripes.sampletomcat;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {

        String markupLocation = "src/main/resources/webapp";
        Tomcat tomcat = new Tomcat();

        // Web Port
        String webPort = System.getenv("INVENTUR_PORT");
        if(webPort == null)
            webPort = "8080";
        tomcat.setPort(Integer.valueOf(webPort));

        // Hostname
        String hostname = System.getenv("INVENTUR_HOSTNAME");
        if (hostname == null)
            hostname = "0.0.0.0";
        tomcat.setHostname(hostname);

        // Default Context
        StandardContext context = (StandardContext) tomcat.addWebapp("", new File(markupLocation).getAbsolutePath());
        System.out.println("configuring app with basedir: " + new File("./" + markupLocation).getAbsolutePath());

        // Alternative "WEB-INF/classes"
        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resource = new StandardRoot(context);
        resource.addPreResources(new DirResourceSet(resource, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
        context.setResources(resource);

        // Start Tomcat
        tomcat.start();
        tomcat.getServer().await();
    }
}
