package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.Set;

public class c_operator{

    public WebDriver operator(String brower) throws InterruptedException {
        choose_IE es = new choose_IE();
        WebDriver driver=null;
        if (brower == "chrome") {
             driver = es.chromeLogin();
        } else if (brower == "firefox") {
             driver = es.firefoxLogin();
        }
        return driver;

    }
    public EdgeDriver operator() throws InterruptedException {
        choose_IE es = new choose_IE();
        EdgeDriver driver = null;
        //edge的访问
        driver = es.edgeLogin();
        return driver;

    }

    public void c_login(EdgeDriver driver){

        driver.navigate().to("http://10.100.14.25:8018/#/login");

        WebElement c_user = driver.findElement(By.name("username"));
        WebElement c_pass = driver.findElement(By.name("password"));
        c_user.sendKeys("cai1");
        c_pass.sendKeys("1234!abc");

        WebElement c_login_button = driver.findElement(By.cssSelector("button"));
        c_login_button.click();
//
//        textBox.sendKeys("Selenium");
//        submitButton.click();

    }

    public void c_handle(EdgeDriver driver){
        // 获取所有窗口的句柄集合
        Set<String> allWindowHandles = driver.getWindowHandles();
        // 切换到新窗口或标签页（通常需要排除当前窗口的句柄）
        for (String handle : allWindowHandles) {
            if (!driver.getWindowHandle().equals(handle)) { // 排除当前窗口的句柄，切换到新窗口或标签页
                driver.switchTo().window(handle); // 切换到新窗口或标签页进行操作
                break; // 通常只需要切换一次，找到新窗口即可，所以使用break退出循环
            }
        }
    }

}
