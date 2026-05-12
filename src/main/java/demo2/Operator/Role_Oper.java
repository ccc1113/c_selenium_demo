package demo2.Operator;

import demo2.Index_WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class Role_Oper {
    Index_WebElement c_index_ele;
    WebElement c_iele;
    WebElement c_element;
    int c_index;

    C_Operator c_oper=new C_Operator();


    public void click_jsgl(WebDriver driver) throws InterruptedException {
        //角色管理
        // 获取所有<div>元素 div[data-v-268e2540] .title 中间一定要有空格
        //进入角色管理
        System.out.println("进入角色管理");
        String a_system="li.el-menu-item > span";
        c_index_ele=c_oper.get_Element_index(driver,a_system,"角色管理","css");
        c_iele=c_index_ele.Element;
        c_iele.click();
    }


    public void insert_role(WebDriver driver,String role_name,String role_code) throws Exception {
        Thread.sleep(1000);
        //点击添加角色按钮
        c_element=driver.findElement(By.xpath("//span[contains(text(),'添加角色')]"));
        //进入添加角色页面
        c_element.click();

        //传入角色代码+角色名称
        role_ty(driver,role_name,role_code);

        Thread.sleep(500);
        //点击创建+确定按钮
        c_element=driver.findElement(By.xpath("//span[contains(text(), '创建')]"));
        c_element.click();
        Thread.sleep(500);
        //二次确定按钮是没法点击的，只能点击它的父级-button
        c_element=driver.findElement(By.xpath("//span[contains(text(), ' 确定')]"));
        WebElement button=c_element.findElement(By.xpath(".."));
        button.click();
        //点击取消按钮
        //c_element=driver.findElement(By.xpath("//span[contains(text(), '取消')]"));
        //c_element.click();

    }

    public void edit_role(WebDriver driver,String yuan_code,String role_name,String role_code) throws Exception {
        Thread.sleep(1000);
        //定位选择的角色代码的位置
        String z_rolecode="//div[text()='"+yuan_code+"']";
        c_element=driver.findElement(By.xpath(z_rolecode));

        //点击其编辑按钮
        WebElement edit=c_element.findElement(By.xpath("../..//span[contains(text(),'编辑')]"));
        //进入编辑角色页面
        edit.click();

        //传入角色代码+角色名称
        role_ty(driver,role_name,role_code);

        //点击确定+二次确定按钮
        Thread.sleep(500);
        c_element=driver.findElement(By.xpath("//span[text()='确定']"));
        c_element.click();

        Thread.sleep(500);
        c_element=driver.findElement(By.xpath("//span[contains(text(), ' 确定')]"));
        c_element.click();
        //点击取消按钮
        //c_element=driver.findElement(By.xpath("//span[contains(text(), '取消')]"));
        //c_element.click();

    }

    public void select_role(WebDriver driver,String sysname,String role_name,String role_code) throws Exception{
        Thread.sleep(1000);
        //查询条件-系统名称-不能为空
        //点击选择系统+传入系统名称
        String choose_sysname="div.el-input > input[placeholder='请选择']";
        c_element=driver.findElement(By.cssSelector(choose_sysname));
        Thread.sleep(500);
        c_element.click();
        c_element.clear();
        c_element.sendKeys(sysname);

        Thread.sleep(3000);
        String z_sysname="//span[text()='"+sysname+"']";
        c_element=driver.findElement(By.xpath(z_sysname));
        c_element.click();

        //查询条件-角色名称
        String z_role_name="//div[@class='el-row']//input[@placeholder='请输入角色名称']";
        c_element=driver.findElement(By.xpath(z_role_name));
        if (role_name==null){
            c_element.clear();
            System.out.println("查询条件-角色名称为空，默认查询全部");
        }else {
            c_element.clear();
            c_element.sendKeys(role_name);
        }

        //查询条件-角色代码
        String z_role_code="//div[@class='el-row']//input[@placeholder='请输入角色代码']";
        c_element=driver.findElement(By.xpath(z_role_code));
        c_element.clear();
        if (role_code==null){
            c_element.clear();
            System.out.println("查询条件-角色代码为空，默认查询全部");
        }else {
            c_element.clear();
            c_element.sendKeys(role_code);
        }

        Thread.sleep(1500);
        //点击查询按钮
        c_element=driver.findElement(By.xpath("//span[contains(text(),'查询')]"));
        c_element.click();

    }

    public void role_ty(WebDriver driver,String role_name,String role_code) throws Exception{
        //角色  添加+编辑 功能的通用部分

        Thread.sleep(2000);
        //角色代码
        String z_role_code="//div[@class='el-form-item__content']//input[contains(@placeholder,'请输入角色代码')]";
        c_element=driver.findElement(By.xpath(z_role_code));
        c_element.clear();
        c_element.sendKeys(role_code);


        //角色名称
        String z_role_name="//div[@class='el-form-item__content']//input[@placeholder='请输入角色名称']";
        c_element=driver.findElement(By.xpath(z_role_name));
        c_element.clear();
        c_element.sendKeys(role_name);


        //角色描述为非必填，故暂不细写
    }


    public void role_user(WebDriver driver,String role_code,String usercode) throws Exception{
        Thread.sleep(1000);
        //定位选择的角色代码的位置
        String z_rolecode="//div[text()='"+role_code+"']";
        c_element=driver.findElement(By.xpath(z_rolecode));

        Thread.sleep(1000);
        //点击其 角色管理用户按钮
        WebElement role_u=c_element.findElement(By.xpath("../..//span[contains(text(),'角色关联用户')]"));
        role_u.click();

        //传入想要添加的管理员账号后等待账号查询成功
        c_element=driver.findElement(By.xpath("//input[@placeholder='请输入要查询的账号']"));
        c_element.sendKeys(usercode);
        Thread.sleep(1500);

        //账号查询成功并选择
        // 不太好定位账号，所以直接用contains吧，可以包含账号信息
        driver.findElement(By.xpath("//li[contains(text(), '"+usercode+"')]")).click();
    }

    public void role_user_insert(WebDriver driver,String role_code,String usercode) throws Exception{
        //进入用户关联用户页面并选中账号
        role_user(driver,role_code,usercode);

        Thread.sleep(1000);
        //点击添加按钮+二次确定按钮
        c_element=driver.findElement(By.xpath("//div[@class='el-row']//span[contains(text(),'添加')]"));
        c_element.click();

        Thread.sleep(500);
        c_element=driver.findElement(By.xpath("//span[contains(text(), ' 确定')]"));
        c_element.click();


        Thread.sleep(500);
        //关掉该页面 （用户、角色关联的下一个兄弟-button）
        String close="//span[contains(text(), '用户、角色关联')]/following-sibling::button";
        c_element=driver.findElement(By.xpath(close));
        c_element.click();
    }

    public void role_user_delete(WebDriver driver,String role_code,String usercode) throws Exception{
        //进入用户关联用户页面
        role_user(driver,role_code,usercode);

        //查询此用户是否存在
        Thread.sleep(3000);

        //点击查询按钮
        //有两个查询，所以需要多看几个父级，不然定位不上
        WebElement list=driver.findElement(By.xpath("//div[@class='el-dialog__body']//span[contains(text(),'查询')]"));
        list.click();

        Thread.sleep(500);
        try {
            //查询结果为空
            c_element=driver.findElement(By.xpath("//span[@class='el-table__empty-text' and text()='暂无数据']"));
            System.out.println("此用户未关联该角色");
        }catch (Exception e){
            c_element=driver.findElement(By.xpath("//span[contains(text(),' 删除')]"));
            //c_element=driver.findElement(By.xpath("//td[@class='is-center']//span[contains(text(),'  删除')]"));
            //c_element=driver.findElement(By.xpath("//span[contains(text(),' 删除 ')]"));
            //System.out.println("size: "+c_element.getSize());
            System.out.println("text="+c_element.getText());
            c_element.click();
        }

        Thread.sleep(500);
        //删除二次确定按钮
        c_element=driver.findElement(By.xpath("//span[contains(text(), ' 确定')]"));
        c_element.click();


        Thread.sleep(500);
        //关掉该页面 （用户、角色关联的下一个兄弟-button）
        String close="//span[contains(text(), '用户、角色关联')]/following-sibling::button";
        c_element=driver.findElement(By.xpath(close));
        c_element.click();


    }


    public void delete_role(WebDriver driver,String yuan_code) throws Exception{
        Thread.sleep(1000);
        //定位选择的角色代码的位置
        String z_rolecode="//div[text()='"+yuan_code+"']";
        c_element=driver.findElement(By.xpath(z_rolecode));

        Thread.sleep(1000);
        //点击其删除按钮
        WebElement delete=c_element.findElement(By.xpath("../..//span[contains(text(),'删除')]"));
        delete.click();

        Thread.sleep(1000);
        //二次确定按钮
        c_element=driver.findElement(By.xpath("//span[contains(text(), ' 确定')]"));
        c_element.click();
        //点击取消按钮
        //c_element=driver.findElement(By.xpath("//span[contains(text(), '取消')]"));
        //c_element.click();
    }

}
