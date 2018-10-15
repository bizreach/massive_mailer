package com.odde.massivemailer.controller.config;

import java.io.Serializable;

public class ApplicationConfiguration implements Serializable {
    public ApplicationConfiguration() {
    }

    public String getRoot() {
        String env = System.getProperty("active_env");
        if ("test".equals(env))
            return "http://localhost:8060/";
        return "http://localhost:8070/";
    }
}