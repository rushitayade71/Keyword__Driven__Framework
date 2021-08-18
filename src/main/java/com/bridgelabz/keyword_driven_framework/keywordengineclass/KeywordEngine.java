package com.bridgelabz.keyword_driven_framework.keywordengineclass;

import com.bridgelabz.keyword_driven_framework.baseclass.BaseClass;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class KeywordEngine extends BaseClass {

        public WebDriver driver;
        public Properties properties;

        public static Workbook book;
        public static Sheet sheet;

        public BaseClass base;
        public WebElement element;

        public void startExecution(String SheetName) throws Exception {
            String locatorName = null;
            String locatorValue = null;

            FileInputStream file = null;
            try {
                file = new FileInputStream("ExcelData/Facebook.xlsx");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                book = WorkbookFactory.create(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            sheet = book.getSheet(SheetName);

            int k = 0;
            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                try {
                    String locatorColValue = sheet.getRow(i + 1).getCell(k + 1).toString().trim();
                    if (!locatorColValue.equalsIgnoreCase("NA")) {
                        locatorName = sheet.getRow(i + 1).getCell(k + 1).toString().trim();
                        locatorValue = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
                    }
                    String action = sheet.getRow(i + 1).getCell(k + 3).toString().trim();
                    String value = sheet.getRow(i + 1).getCell(k + 4).toString().trim();

                    switch (action) {
                        case "Open browser":
                            base = new BaseClass();
                            properties = base.init_properties();
                            if (value.isEmpty() || value.equals("NA")) {
                                driver = base.init_driver(properties.getProperty("browser"));
                            } else {
                                driver = base.init_driver(value);
                            }
                            break;
                        case "enter url":
                            if (value.isEmpty() || value.equals("NA")) {
                                driver.get(properties.getProperty("url"));
                            } else {
                                driver.get(value);
                            }
                            break;

                        case "quit":
                            driver.quit();
                            break;
                        default:
                            break;
                    }
                    switch (locatorName) {
                        case "id":
                            element = driver.findElement(By.id(locatorValue));
                            if (action.equalsIgnoreCase("sendkeys")) {
                                element.clear();
                                element.sendKeys(value);
                            } else if (action.equalsIgnoreCase("click")) {
                                element.click();
                            }
                            locatorName = null;
                            break;
                        case "name":
                            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                            element = driver.findElement(By.name(locatorValue));
                            if (action.equalsIgnoreCase("sendkeys")) {
//                                element.clear();
                                element.sendKeys(value);
                            } else if (action.equalsIgnoreCase("click")) {
                                element.click();
                            }
                            locatorName = null;
                            break;
                        case "xpath":
                            element = driver.findElement(By.xpath(locatorValue));
                            if (action.equalsIgnoreCase("sendkeys")) {
                                element.clear();
                                element.sendKeys(value);
                            } else if (action.equalsIgnoreCase("click")) {
                                element.click();
                            }
                            locatorName = null;
                            break;
                        case "cssSelector":
                            element = driver.findElement(By.cssSelector(locatorValue));
                            if (action.equalsIgnoreCase("sendkeys")) {
                                element.clear();
                                element.sendKeys(value);
                            } else if (action.equalsIgnoreCase("click")) {
                                element.click();
                            }
                            locatorName = null;
                            break;
                        case "tagName":
                            element = driver.findElement(By.tagName(locatorValue));
                            if (action.equalsIgnoreCase("sendkeys")) {
                                element.clear();
                                element.sendKeys(value);
                            } else if (action.equalsIgnoreCase("click")) {
                                element.click();
                            }
                            locatorName = null;
                            break;
                        default:
                            break;
                    }

                } catch (Exception e) {
                }
            }
            System.out.println(+sheet.getLastRowNum());
        }
    }
