package demo2.Operator;

import demo2.Index_WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

public class Zuzhi_Oper {
    Index_WebElement c_index_ele;
    WebElement c_iele;
    WebElement c_element;
    int c_index;

    C_Operator c_oper=new C_Operator();


    public void click_zzgl(WebDriver driver) throws InterruptedException {
        //组织管理
        // 获取所有<div>元素 div[data-v-268e2540] .title 中间一定要有空格
        //进入组织管理
        System.out.println("进入组织管理");
        String a_zuzhi="li.el-menu-item > span";
        c_index_ele=c_oper.get_Element_index(driver,a_zuzhi,"组织管理","css");
        c_iele=c_index_ele.Element;
        c_iele.click();
    }

    public void edit_kg(WebDriver driver) throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("编辑状态开关为-开");
        //开关为复选框格式，故可进行判断，isSelected
        String a_checkbox="el-switch__input";
        c_element=driver.findElement(By.className(a_checkbox));
        boolean isChecked = c_element.isSelected();
        if (isChecked!=true){
            //开关为关时，打开它
            //需注意：开关点击是复选框下的span，所以需重新获取元素
            c_element=driver.findElement(By.cssSelector(".el-switch__core"));
            c_element.click();
            System.out.println("已打开开关");
        }
    }

    public void open_tree(WebDriver driver,String zuzhi_daima) throws InterruptedException {
        Thread.sleep(3000);
        /*向上找共同父容器：/ancestor::div[contains(@class,'el-tree-node__content')]；
            ancestor::表示 “向上找所有祖先元素”，精准匹配包含el-tree-node__content类的<div>（这是a2_daima和目标按钮的共同父容器）。
          向下找目标按钮：/span[contains(@class,'el-icon-caret-right')]
            在共同父容器下，直接找包含el-icon-caret-right类的<span>（即目标按钮）。
        */
        //未展开的元素定位，el-icon-caret-right一定存在
        String zhankai_close="//span[contains(text(),'"+zuzhi_daima+"')]/ancestor::div[contains(@class,'el-tree-node__content')]/span[contains(@class,'el-icon-caret-right')]";
        //已展开的元素定位：expanded只在展开时出现，未展开时不出现
        String zhankai_open="//span[contains(text(),'"+zuzhi_daima+"')]/ancestor::div[contains(@class,'el-tree-node__content')]/span[contains(@class,'expanded')]";
        //已展开的元素定位：is-leaf代表下面无层级，无法展开
        String zhankai_null="//span[contains(text(),'"+zuzhi_daima+"')]/ancestor::div[contains(@class,'el-tree-node__content')]/span[contains(@class,'is-leaf')]";
        try{
            c_element= driver.findElement(By.xpath(zhankai_open));
            System.out.println("组织代码"+zuzhi_daima+"已展开,无需再次点击");
        }catch (NoSuchElementException e){
            //判断是否需展开
            try{
                c_element= driver.findElement(By.xpath(zhankai_null));
                System.out.println("组织代码"+zuzhi_daima+"无展开按钮");
            }catch(NoSuchElementException e1){
                c_element= driver.findElement(By.xpath(zhankai_close));
                System.out.println("组织代码"+zuzhi_daima+"未展开,已点击展开");
                c_element.click();
            }

           // System.out.println("该组织代码-"+daima+"无展开按钮或已展开");
        }
    }

    public void insert_zz(WebDriver driver,String yuandaima,String name,String daima) throws InterruptedException {
        //判断组织树的根组织是否展开，没展开则点击使其展开
        open_tree(driver,"GZZ");

        Thread.sleep(3000);
        //判断一级目录是否存在
        try {
            WebElement child_element = driver.findElement(By.xpath("//span[contains(text(), '" + yuandaima + "')]"));
            Thread.sleep(500);
            if (yuandaima.equals(daima)){
                System.out.println(yuandaima+":该组织代码已存在,不再新增同一组织代码");
            }else{
                System.out.println(yuandaima+":该组织代码已存在，本次新建"+daima+"为二级组织代码");
                c_element = child_element.findElement(By.xpath("../div[@class='ly-visible']/span/li[1]"));
                System.out.println("class为： " + c_element.getAttribute("class"));
                Thread.sleep(1500);
                c_element.click();
                //新增二级目录
                insert_zz_sendkeys(driver,name,daima);
            }
        }catch(NoSuchElementException e){
            System.out.println(yuandaima+":该组织代码不存在，本次新建此组织代码为一级组织代码");
            //查找根目录
            c_index_ele=c_oper.get_Element_index(driver,".ly-tree-node > span","根组织","css");
            //获取根目录对应下标
            int j=c_index_ele.index;
            //点击对应 + 号 （根目录对应的+号）
            //nth-of-type 方法必须从1开始，故 int j=i+1
            String a_jiahao=".ly-tree-node > div > .ly-visible:nth-of-type("+j+") > span > .el-icon-plus";
            c_element=driver.findElement(By.cssSelector(a_jiahao));
            //点击对应 + 号
            c_element.click();

            //新增一级目录
            insert_zz_sendkeys(driver,name,daima);
        }

        Thread.sleep(3000);
        //点击提交按钮
        c_element= driver.findElement(By.xpath("//span[text()='提交']"));
        c_element.click();

    }

    public void insert_zz_sendkeys(WebDriver driver,String name,String daima) throws InterruptedException {
        Thread.sleep(3000);
        //传入组织名称和组织代码
        //由于 .el-form-item--medium:nth-of-type(1)对应的是根组织，无input，故分别为2 3
        //xpath和cssselector，这两种写法都可以，但是个人感觉xpath好用一点
        //String a_zuzhi_name_xpath=".el-form-item--medium:nth-of-type(2) > div > .el-input--medium > input";
        String a_zuzhi_name="//input[@class='el-input__inner' and @placeholder='请输入组织名称']";
        c_element= driver.findElement(By.xpath(a_zuzhi_name));
        //验证数据
        //System.out.println("读取文本框中的内容:  "+c_element.getAttribute("placeholder"));
        c_element.sendKeys(name);

        //String a_zuzhi_daima=".el-form-item--medium:nth-of-type(3) > div > .el-input--medium > input";
        String a_zuzhi_daima="//input[@class='el-input__inner' and @placeholder='请输入组织代码']";
        c_element= driver.findElement(By.xpath(a_zuzhi_daima));
        //验证数据
        //System.out.println("读取文本框中的内容:  "+c_element.getAttribute("placeholder"));
        c_element.sendKeys(daima);

        //取消功能暂不过多验证
//        String a_zuzhi_cancel="//span[text()='取消']";
//        WebElement c1= driver.findElement(By.xpath(a_zuzhi_cancel));
//        c1.click();

        Thread.sleep(500);
        //通过xpath定位元素
        String a_zuzhi_create="//span[text()='创建']";
        c_element= driver.findElement(By.xpath(a_zuzhi_create));
        c_element.click();

        Thread.sleep(500);
        //创建后弹出二次确定弹框，点击确认
        String a_ok=".el-message-box__btns > button.el-button--primary";
        c_element=driver.findElement(By.cssSelector(a_ok));
        c_element.click();

        System.out.println(daima+"新增成功");

    }


    public void delete_zz(WebDriver driver,String daima) throws InterruptedException {

        Thread.sleep(3000);
        //判断组织代码是否存在
        try {
            WebElement child_element = driver.findElement(By.xpath("//span[contains(text(), '" + daima + "')]"));
            //li[1]是新增，2是编辑，3是删除；点击删除按钮
            c_element = child_element.findElement(By.xpath("../div[@class='ly-visible']/span/li[3]"));
            System.out.println("class=： " + c_element.getAttribute("class"));
            //删除按钮被遮挡，故使用JS执行点击（彻底绕过拦截/遮挡）
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", c_element);

            Thread.sleep(500);
            //删除后弹出二次确定弹框，点击确认
            String a_ok=".el-message-box__btns > button.el-button--primary";
            c_element=driver.findElement(By.cssSelector(a_ok));
            c_element.click();
        }catch(NoSuchElementException e){
            System.out.println("该组织代码-"+daima+"不存在，无法删除");
        }

        Thread.sleep(3000);
        //点击提交按钮
        c_element= driver.findElement(By.xpath("//span[text()='提交']"));
        c_element.click();
    }



}
