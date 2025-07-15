package step.definitions;

import io.restassured.RestAssured;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseStepDefinition {

    public static Properties properties;

    static {
        try {
            properties = new Properties();
            properties.load(new FileInputStream(new File(System.getProperty("user.dir")+"//src//test//resources//test.properties")));
            RestAssured.baseURI = properties.getProperty("base.url");
        } catch (Exception e) {
            System.out.println("Exception occurred while reading the data from properties file: " + e.getMessage());
        }

    }
}
