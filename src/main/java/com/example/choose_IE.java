package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class choose_IE {
    public EdgeDriver edgeLogin(){
        //microsoft edge浏览器控制
        System.setProperty("webdriver.edge.driver", "D:\\cc_java_demo\\c_selenium\\c_selenium_demo\\src\\test\\resources\\drivers\\msedgedriver.exe");
        EdgeDriver driver = new EdgeDriver();
        return driver;
    }

    public WebDriver chromeLogin(){
        //chrome浏览器控制
        System.setProperty("webdriver.chrome.driver", "D:\\cc_java_demo\\c_selenium\\c_selenium_demo\\src\\test\\resources\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    public WebDriver firefoxLogin(){
        //firefox浏览器控制
        System.setProperty("webdriver.geckodriver.driver", "D:\\cc_java_demo\\c_selenium\\c_selenium_demo\\src\\test\\resources\\drivers\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        return driver;
    }


}
