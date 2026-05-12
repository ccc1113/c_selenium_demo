package demo2.Operator;

import demo2.Index_WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class C_Operator {
    //选择浏览器控制
    public WebDriver operator(String brower) throws InterruptedException {
        WebDriver driver=null;
        if (brower == "chrome") {
            //chrome浏览器控制
            System.setProperty("webdriver.chrome.driver", "D:\\cc_java_demo\\c_selenium\\c_selenium_demo\\src\\test\\resources\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (brower == "firefox") {
            //firefox浏览器控制
            System.setProperty("webdriver.geckodriver.driver", "D:\\cc_java_demo\\c_selenium\\c_selenium_demo\\src\\test\\resources\\drivers\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (brower == "edge") {
            //microsoft edge浏览器控制
            System.setProperty("webdriver.edge.driver", "D:\\cc_java_demo\\c_selenium\\c_selenium_demo\\src\\test\\resources\\drivers\\msedgedriver.exe");
            driver = new EdgeDriver();
        }

        return driver;
    }


    //登录sso页面
    public void c_login(WebDriver driver) throws InterruptedException {

        driver.navigate().to("http://10.100.14.25:8018/#/login");
        driver.manage().window().maximize();
        Thread.sleep(3000);
        //设置页面的加载时间，最多10s，超过则直接抛出异常
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        WebElement c_user = driver.findElement(By.name("username"));
        WebElement c_pass = driver.findElement(By.name("password"));
        c_user.sendKeys("cai1");
        c_pass.sendKeys("1234!abc");

        WebElement c_login_button = driver.findElement(By.cssSelector("button"));
        c_login_button.click();

    }

    //切换句柄
    public void c_handle(WebDriver driver) throws InterruptedException {
        Thread.sleep(3000);
        //设置页面的加载时间，最多10s，超过则直接抛出异常
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        // 获取所有窗口的句柄集合
        Set<String> allWindowHandles = driver.getWindowHandles();
        // 切换到新窗口或标签页（通常需要排除当前窗口的句柄）
        for (String handle : allWindowHandles) {
            System.out.println("本次句柄值为："+handle);
            if (!driver.getWindowHandle().equals(handle)) { // 排除当前窗口的句柄，切换到新窗口或标签页
                driver.switchTo().window(handle); // 切换到新窗口或标签页进行操作
                break; // 通常只需要切换一次，找到新窗口即可，所以使用break退出循环
            }
        }
    }

    //获取全部Elements，并匹配出想要的数据
    public Index_WebElement get_Element_index(WebDriver driver, String key, String value, String type) throws InterruptedException {
        Thread.sleep(3000);
        Index_WebElement index_w=new Index_WebElement();
        WebElement c_by_which=null;
        List<WebElement> c_by_which_two=null;
        //判断使用cssselector还是xpath，进行定位
        if (type.equals("css")){
            c_by_which=driver.findElement(By.cssSelector(key));
            c_by_which_two=driver.findElements(By.cssSelector(key));
        } else if (type.equals("xpath")) {
            c_by_which=driver.findElement(By.xpath(key));
            c_by_which_two=driver.findElements(By.xpath(key));
        }

        if (value.isEmpty()){
            //如果value为null，则匹配出key对应的WebElement即可
            index_w.Element=c_by_which;
            System.out.println("本次css表达式为：" + key);
        }else{
            //如果value不为null，则匹配出value对应的WebElement及其下标
            //获取所需key对应的下标
            for (int i=0;i<c_by_which_two.size();i++) {
                if (c_by_which_two.get(i).getText().equals(value)){
                    //点击对应 + 号
                    //nth-of-type 方法必须从1开始，故 int j=i+1
                    int j=i+1;
                    index_w.index=i+1;
                    index_w.Element=c_by_which_two.get(i);
                    System.out.println("遍历的值为："+c_by_which_two.get(i).getText()+" ,本次css表达式为：" + key+" ,对应下标为"+j);
                    break;
                }

            }
        }
        return index_w;
    }

}

