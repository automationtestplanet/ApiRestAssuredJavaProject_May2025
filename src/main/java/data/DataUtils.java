package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import models.NewUserDetails;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class DataUtils {
    public static NewUserDetails getNewUserDetails(String jsonFilePath) {
        try {
            ObjectMapper objMap = new ObjectMapper();
            return objMap.readValue(Paths.get(jsonFilePath).toFile(), NewUserDetails.class);
        } catch (Exception e) {
            System.out.println("Exception occurred while reading the data from Json file: " + e.getMessage());
            return null;
        }
    }

    public static String readDataFromJson(String jsonFilePath) {
        try {
            URL resourceUrl = Resources.getResource(jsonFilePath);
            return Resources.toString(resourceUrl, Charsets.UTF_8);
        } catch (Exception e) {
            System.out.println("Exception occurred while reading the data from Json file: " + e.getMessage());
            return null;
        }
    }
}
