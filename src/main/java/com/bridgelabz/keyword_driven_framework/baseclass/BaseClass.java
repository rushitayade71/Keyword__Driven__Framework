package com.bridgelabz.keyword_driven_framework.baseclass;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
        //initialize driver,properties
        public WebDriver driver;
        public Properties properties;

        public WebDriver init_driver(String browserName) throws Exception {
            if (browserName.equalsIgnoreCase("chrome"))
            {
                WebDriverManager.chromedriver().setup();
                driver=new ChromeDriver();
            }
            else if (browserName.equalsIgnoreCase("firefox"))
            {
                WebDriverManager.firefoxdriver().setup();
                driver=new FirefoxDriver();
            }
            else
            {
                throw new Exception("Incorrect Browser!!");
            }
            Thread.sleep(5000);
            return driver;
        }

        public Properties init_properties()
        {
            properties =new Properties();
            try {
                FileInputStream ip=new FileInputStream("src/main/java/com/bridgelabz/keyword_driven_framework/config/config.properties");
                properties.load(ip);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return properties;
        }

    }

