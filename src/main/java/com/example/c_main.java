package com.example;

import demo2.Operator.C_Operator;
import demo2.Index_WebElement;
import demo2.Operator.System_Oper;
import demo2.Operator.Zuzhi_Oper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class c_main {


    public  void Choose() throws InterruptedException {
        //选择浏览器
        choose_IE es=new choose_IE();
        //edge的访问
        EdgeDriver driver = es.edgeLogin();
        /*
            //chrome的访问
            WebDriver driver1 = es.chromeLogin();
            //firefox的访问
            WebDriver driver2 = es.firefoxLogin();
        */
    }
    public static void main(String[] args) throws Exception {
        c_operator cc=new c_operator();
        //选择浏览器
        EdgeDriver driver= cc.operator();
       Thread.sleep(3000);
        //设置页面的加载时间，最多10s，超过则直接抛出异常
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        //切换句柄
        cc.c_handle(driver);

        // 在新窗口或标签页中执行操作...
        // ...例如获取新页面的title等...
        System.out.println("Title of the new window: " + driver.getTitle());

        // 获取所有<div>元素 div[data-v-268e2540] .title 中间一定要有空格
        List<WebElement> c_sso= driver.findElements(By.cssSelector("div[data-v-268e2540] .title"));
        // 遍历并打印所有<div>元素的文本,进入权限管理系统
        for (WebElement c_sso1 : c_sso) {
            if(c_sso1.getText().equals("权限管理系统")){
                System.out.printf("进入权限管理系统"+c_sso1.getText());
                c_sso1.click();
                break;
            }
        }

        //切换句柄
        cc.c_handle(driver);

        //执行用户查询




       // driver.quit();

    }

    public static class C_Main {

        public static void zzgl_test(WebDriver driver) throws InterruptedException {
            //组织管理相关
            Zuzhi_Oper zuzhiOper=new Zuzhi_Oper();
            //进入组织管理
            zuzhiOper.click_zzgl(driver);
            //编辑状态开关为-开
            zuzhiOper.edit_kg(driver);



            //创建组织架构（目前仅支持新增一二级，暂不支持新增三四五六七级）
            //点击组织树，使其展开（根组织旁边的展开按钮）并点击根目录对应的加号
            //创建不存在的组织(一级)a1_dm不存在，建a1_dm
            zuzhiOper.insert_zz(driver,"a1_dm","a1_name","a1_dm");
            //a1_dm已存在，建a2_name
            zuzhiOper.insert_zz(driver,"a1_dm","a2_name","a2_dm");
            //a1_dm已存在，建a3_name
            zuzhiOper.insert_zz(driver,"a1_dm","a3_name","a3_dm");

            //展开所有层级
            zuzhiOper.open_tree(driver,"GZZ");
            zuzhiOper.open_tree(driver,"a1_dm");
            zuzhiOper.open_tree(driver,"a2_dm");
            zuzhiOper.open_tree(driver,"a3_dm");

            //删除组织
            zuzhiOper.delete_zz(driver,"a2_dm");
        }

        public static void main(String[] args) throws Exception {
            Index_WebElement c_index_ele;
            WebElement c_iele;
            WebElement c_element;
            int c_index;
            C_Operator c_oper=new C_Operator();
            //选择浏览器
            EdgeDriver driver= (EdgeDriver) c_oper.operator("edge");

            //登录sso
            System.out.println("登录sso");
            c_oper.c_login(driver);
            //切换句柄
            System.out.println("切换句柄");
            c_oper.c_handle(driver);

            // 在新窗口或标签页中执行操作...
            // ...例如获取新页面的title等...
            System.out.println("获取本次页面的title");
            System.out.println("Title of the new window: " + driver.getTitle());
            Thread.sleep(3000);
            // 获取所有<div>元素 div[data-v-268e2540] .title 中间一定要有空格
            //进入权限管理系统
            System.out.println("进入权限管理系统");
            String a_system="div[data-v-268e2540] .title";
            c_index_ele=c_oper.get_Element_index(driver,a_system,"权限管理系统","css");
            c_iele=c_index_ele.Element;
            c_iele.click();

            //调用组织管理--测试数据
            //zzgl_test(driver);

            //系统管理页面
            System_Oper systemOper=new System_Oper();
            //进入系统管理
            //systemOper.click_xxgl(driver);

            //添加系统页面
            //systemOper.insert_sys(driver,"cai-sys_daima","cai-sys_name","/cai-sys_genlujin","http://cai-shouye_dizhi");

            //查询系统名称
           // systemOper.select_sys_name(driver,"系统名称","cai-sys_name");
















            //Thread.sleep(13000);
            //driver.quit();

        }

    }
}
