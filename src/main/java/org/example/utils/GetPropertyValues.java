package org.example.utils;

import org.example.base.BaseLib;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetPropertyValues {
    private static Properties genericProperties;
    private static Properties environmentProperties;

    private static Logger logger
            = Logger.getLogger(GetPropertyValues.class.getName());

    /**
     * default constructor, it reads configuration
     * properties from the default location i.e.
     * in build environment from ./conf folder &
     * in development environment from resources folder
     *
     * @throws RuntimeException in case of invalid input
     */
    public GetPropertyValues(boolean loadEnvironmentProperties) throws RuntimeException {
        this(null, null, loadEnvironmentProperties);
    }


    /**
     * Constructor to read global properties and
     * global object repository properties
     * from the given file path
     *
     * @param genericPropertiesFile     String
     * @param environmentPropertiesFile String
     * @throws RuntimeException custom exception to make logger more useful
     */
    public GetPropertyValues(String genericPropertiesFile, String environmentPropertiesFile, boolean loadEnvironmentProperties) throws RuntimeException {

        genericProperties = new Properties();
        environmentProperties = new Properties();

        InputStream inputStream = null;
        try {
            if (genericPropertiesFile == null) {
                if (new File("./src/test/resources/generic.properties").exists()) {
                    inputStream = Files.newInputStream(new File(
                            "./src/test/resources/generic.properties").toPath());
                } else {
                    inputStream = getClass().getClassLoader().getResourceAsStream(
                            "generic.properties");
                }
            } else {
                inputStream = Files.newInputStream(new File(genericPropertiesFile).toPath());
            }
            genericProperties.load(inputStream);
            logger.info("Loaded generic properties successfully");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unable to load global properties from \""
                    + genericPropertiesFile + "\"", e);
            throw new RuntimeException(e.getClass().getName()
                    + " -> Unable to load global properties from \""
                    + genericPropertiesFile + "\"", e);
        }
        if (loadEnvironmentProperties) {
            try {
                if (environmentPropertiesFile == null) {
                    if (new File("./src/test/resources/" + BaseLib.globalEnvironment + "_config.properties").exists()) {
                        System.out.println("outside getResourceAsStream");
                        inputStream = Files.newInputStream(new File(
                                "./src/test/resources/" + BaseLib.globalEnvironment + "_config.properties").toPath());
                    } else {
                        System.out.println("inside getResourceAsStream");
                        inputStream = getClass().getClassLoader().getResourceAsStream(
                                BaseLib.globalEnvironment + "_config.properties");
                        System.out.println("inputStream >>" + inputStream);
                    }
                } else {
                    inputStream = Files.newInputStream(new File(environmentPropertiesFile).toPath());
                }
                environmentProperties.load(inputStream);
                logger.info("Loaded environment properties successfully");
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Unable to load environment properties from \""
                        + environmentProperties + "\"", e);
                throw new RuntimeException(e.getClass().getName()
                        + " -> Unable to load environment properties from \""
                        + environmentProperties + "\"", e);
            }
        }
    }

    public static String getPropertyValues(String file, String key) {
        String value = "";
        try {
            Properties prop = new Properties();
            prop.load(Files.newInputStream(new File(file).toPath()));
            value = prop.getProperty(key);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return value;
    }

    /**
     * Get value of given global/dynamic property name
     *
     * @param propertyName String
     * @return String
     */
    public static String getGenericProperty(String propertyName)
            throws RuntimeException {
        String propertyValue = genericProperties.getProperty(propertyName);
        if (propertyValue != null) {
            return propertyValue;
        } else {
            throw new RuntimeException("Generic properties not found with name \""
                    + propertyName + "\"");
        }
    }

    /**
     * Get value of given global/dynamic property name
     *
     * @param propertyName String
     * @return String
     * @throws RuntimeException in case of incorrect inputs and file not found
     */
    public static String getEnvironmentProperty(String propertyName)
            throws RuntimeException {
        String propertyValue = environmentProperties.getProperty(propertyName);
        if (propertyValue != null) {
            return propertyValue;
        } else {
            throw new RuntimeException("Environment properties not found with name \""
                    + propertyName + "\"");
        }
    }
}
