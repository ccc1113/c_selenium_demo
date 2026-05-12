package demo2.Operator;

import demo2.Index_WebElement;
import org.openqa.selenium.*;

import java.util.List;

public class Quanxian_Oper {
    Index_WebElement c_index_ele;
    WebElement c_iele;
    WebElement c_element;
    int c_index;

    C_Operator c_oper=new C_Operator();


    public void click_qxgl(WebDriver driver) throws InterruptedException {
        //权限管理
        // 获取所有<div>元素 div[data-v-268e2540] .title 中间一定要有空格
        //进入权限管理
        System.out.println("进入权限管理");
        String a_zuzhi="li.el-menu-item > span";
        c_index_ele=c_oper.get_Element_index(driver,a_zuzhi,"权限管理","css");
        c_iele=c_index_ele.Element;
        c_iele.click();
    }

    public void choose_sys(WebDriver driver,String name) throws InterruptedException {
        Thread.sleep(1500);
        //点击系统的下拉框
        c_element=driver.findElement(By.xpath("//input[@class='el-input__inner' and @placeholder='请选择']"));
        c_element.click();
        Thread.sleep(1500);
        //选择系统
        c_element=driver.findElement(By.xpath("//span[text()='"+name+"']"));
        //使用javascript滚动至元素位置
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", c_element);
        Thread.sleep(1500);
        c_element.click();
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

    public void open_tree(WebDriver driver,String qxname) throws InterruptedException {
        Thread.sleep(1500);
        //定位当前权限名
        String qxname_dizhi="//span[text()='"+qxname+"']";
        try{
            c_element= driver.findElement(By.xpath(qxname_dizhi));

            //定位当前权限名的展开按钮 (层级关系为 cd ../../../div/span)
            // 具体显示为 span-->span-custom-tree-node-->div-treeitem/div/span
            String fuji="../../..";
            WebElement fuji_ele=c_element.findElement(By.xpath(fuji));
            System.out.println("fuji+"+fuji_ele.getAttribute("class"));
            //由于span中有多个class，所以需要contains判断是否存在，而不是精确判断
            String zhankai="./span[contains(@class,'el-icon-caret-right')]";
            WebElement zhankai_ele=fuji_ele.findElement(By.xpath(zhankai));
            //获取展开按钮的class值，并进行判断
            //未展开的元素定位，el-icon-caret-right一定存在
            //已展开的元素定位：expanded只在展开时出现，未展开时不出现
            //已展开的元素定位：is-leaf代表下面无层级，无法展开
            String value=zhankai_ele.getAttribute("class");
            boolean zhankai_open=value.contains("expanded");
            boolean zhankai_null=value.contains("is-leaf");
            if (zhankai_open==true){
                System.out.println("权限名称"+qxname+"已展开,无需再次点击");
            } else if (zhankai_null==true) {
                System.out.println("权限名称"+qxname+"无展开按钮");
            } else {
                zhankai_ele.click();
                System.out.println("权限名称"+qxname+"未展开,已点击展开");
            }

        }catch (Exception e){
            System.out.println(qxname+":此权限名不存在，请新建");
        }


    }

    public void insert_qx(WebDriver driver,String yiji_mulu,String qx_name,String qx_value,int qx_type,String qx_lujing,int jianquan_type) throws InterruptedException {
        //打开开关
        edit_kg(driver);

        Thread.sleep(3000);
        c_element=driver.findElement(By.xpath("//span[text()='"+yiji_mulu+"']"));
        //定位当前权限名对应的添加按钮
        String fuji="../..";
        WebElement fuji_ele=c_element.findElement(By.xpath(fuji));
        //当前父级中存在 i.el-icon-plus;此为添加按钮
        String insert=".//i[@class='el-icon-plus']";
        WebElement insert_ele=fuji_ele.findElement(By.xpath(insert));
        //i 的上级为button，选择button进行click
        String button="..";
        WebElement button_ele=insert_ele.findElement(By.xpath(button));
        //点击进入添加页面
        button_ele.click();


        //添加权限
        qx_sendkeys(driver,qx_name,qx_value,qx_type,qx_lujing,jianquan_type);

    }

    public void edit_qx(WebDriver driver,String qx_name,String n_qx_name,String n_qx_value,int n_qx_type,String n_qx_lujing,int n_jianquan_type) throws InterruptedException {
        //打开开关
        edit_kg(driver);

        Thread.sleep(1000);
        c_element=driver.findElement(By.xpath("//span[text()='"+qx_name+"']"));
        //定位当前权限名对应的编辑按钮
        String fuji="../..";
        WebElement fuji_ele=c_element.findElement(By.xpath(fuji));
        //当前父级中存在li.el-icon-edit;此为编辑按钮
        String edit=".//i[@class='el-icon-edit']";
        WebElement edit_ele=fuji_ele.findElement(By.xpath(edit));
        String button="..";
        WebElement button_ele=edit_ele.findElement(By.xpath(button));
        //点击进入编辑页面
        button_ele.click();

        //编辑权限
        qx_sendkeys(driver,n_qx_name,n_qx_value,n_qx_type,n_qx_lujing,n_jianquan_type);

    }

    public void qx_sendkeys(WebDriver driver,String qx_name,String qx_value,int qx_type,String qx_lujing,int jianquan_type) throws InterruptedException {
        WebElement xiongdi_ele=null;
        //新增或编辑功能
        Thread.sleep(1500);
        //权限名
        c_element=driver.findElement(By.xpath("//input[@placeholder='请输入权限名']"));
        c_element.clear();
        c_element.sendKeys(qx_name);

        //权限值
        c_element=driver.findElement(By.xpath("//input[@placeholder='请输入权限值']"));
        c_element.clear();
        c_element.sendKeys(qx_value);

        //权限类型
        if (qx_type==0){
            c_element=driver.findElement(By.xpath("//span[text()='菜单' and @class='el-radio__label']"));
        } else if (qx_type==1) {
            c_element=driver.findElement(By.xpath("//span[text()='按钮 ' and @class='el-radio__label']"));
        } else{
            c_element=driver.findElement(By.xpath("//span[text()='菜单' and @class='el-radio__label']"));
            System.out.println("权限类型仅可选择0-菜单或1-按钮,传入其他值则默认选择0");
        }
        //上一个兄弟-span的input元素
        xiongdi_ele= c_element.findElement(By.xpath("./preceding-sibling::span"));
        xiongdi_ele.click();

        //权限请求路径
        c_element=driver.findElement(By.xpath("//input[@placeholder='请输入请求路径']"));
        c_element.clear();
        c_element.sendKeys(qx_lujing);

        //鉴权类型
        if (jianquan_type == 0){
            c_element=driver.findElement(By.xpath("//span[text()='匿名访问' and @class='el-radio__label']"));
        } else if (jianquan_type==1) {
            c_element=driver.findElement(By.xpath("//span[text()='登录后访问' and @class='el-radio__label']"));
        } else if (jianquan_type==2) {
            c_element=driver.findElement(By.xpath("//span[text()='授权访问' and @class='el-radio__label']"));
        } else{
            c_element=driver.findElement(By.xpath("//span[text()='匿名访问' and @class='el-radio__label']"));
            System.out.println("鉴权类型仅可选择：0-匿名访问 1-登陆后访问 2-授权访问,传入错误默认选择0");
        }
        xiongdi_ele= c_element.findElement(By.xpath("./preceding-sibling::span"));
        xiongdi_ele.click();

        Thread.sleep(500);
        //点击确定按钮(注意，这里的精确定位中，带有空格)
        c_element= driver.findElement(By.xpath("//span[text()='确定 ']"));
        c_element.click();

        Thread.sleep(500);
        //二次确定按钮（前后存在空格）
        c_element= driver.findElement(By.xpath("//span[contains(text(),' 确定')]"));
        c_element.click();

        Thread.sleep(500);
        //点击提交按钮
        c_element= driver.findElement(By.xpath("//span[text()='提交']"));
        c_element.click();
        //取消功能暂不过多验证
//        String a_zuzhi_cancel="//span[text()='取消']";
//        WebElement c1= driver.findElement(By.xpath(a_zuzhi_cancel));
//        c1.click();

    }


    public void delete_qx(WebDriver driver,String qxname) throws InterruptedException {
        //打开开关
        edit_kg(driver);

        Thread.sleep(1000);
        c_element=driver.findElement(By.xpath("//span[text()='"+qxname+"']"));
        //定位当前权限名对应的删除按钮
        String fuji="../..";
        WebElement fuji_ele=c_element.findElement(By.xpath(fuji));
        //当前父级中存在i.el-icon-delete;此为删除按钮
        String button=".//i[@class='el-icon-delete']";
        WebElement delete_ele=fuji_ele.findElement(By.xpath(button));
        //点击进入编辑页面
        delete_ele.click();


        Thread.sleep(500);
        //二次确定按钮（前后存在空格）
        c_element= driver.findElement(By.xpath("//span[contains(text(),' 确定')]"));
        c_element.click();

        Thread.sleep(500);
        //点击提交按钮
        c_element= driver.findElement(By.xpath("//span[text()='提交']"));
        c_element.click();

       /* //删除后弹出二次确定弹框，点击确认
        String a_ok=".el-message-box__btns > button.el-button--primary";
        c_element=driver.findElement(By.cssSelector(a_ok));
        c_element.click();
        Thread.sleep(3000);
        //点击提交按钮
        c_element= driver.findElement(By.xpath("//span[text()='提交']"));
        c_element.click();

        */
    }

}
