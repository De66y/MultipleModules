package com.example;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import javax.servlet.ServletException;
import java.io.File;

public class Runner {

    public static void main(String[] args) throws LifecycleException, ServletException {
        String webappDirLocation = "Blok4JavaEE/javaweb/simplewebapp/src/main/webapp/";
        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8082);
        // tomcat.addRole("bram", "user");
        // tomcat.addRole("bram", "admin");
        // tomcat.addUser("bram", "bram");

        StandardContext ctx = (StandardContext) tomcat.addWebapp("/simplewebapp", new File(webappDirLocation).getAbsolutePath());

        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                new File("Blok4JavaEE/javaweb/simplewebapp/target/classes").getAbsolutePath(), "/"));
        ctx.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();
    }
}

