package demo2;

import demo2.Operator.*;
import org.junit.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class c_testng {
    Index_WebElement c_index_ele;
    WebElement c_iele;
    WebElement c_element;
    int c_index;
    C_Operator c_oper=new C_Operator();
    //选择浏览器
    //选择浏览器
    //EdgeDriver driver;
    ChromeDriver driver;


    @Before
    public void login() throws Exception{

        //driver= (EdgeDriver) c_oper.operator("edge");
        driver= (ChromeDriver) c_oper.operator("chrome");
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


    }

    @Test
    public void zuzhi_test() throws Exception{
        //调用组织管理--测试数据
        //组织管理相关
        Zuzhi_Oper zuzhiOper=new Zuzhi_Oper();
        //进入组织管理
        zuzhiOper.click_zzgl(driver);
        //编辑状态开关为-开
        zuzhiOper.edit_kg(driver);


/*
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
        zuzhiOper.delete_zz(driver,"a3_dm");
*/
    }

    @Test
    public void  system_test() throws Exception{

        //系统管理页面
        System_Oper systemOper=new System_Oper();
        //进入系统管理
        systemOper.click_xxgl(driver);

        //添加系统页面
        //systemOper.insert_sys(driver,"cai-sys_daima","cai-sys_name","/cai-sys_genlujin","http://cai-shouye_dizhi");

        //查询系统名称
        //systemOper.select_sys_name(driver,"系统代码","cai-sys_daima");

        //编辑系统
        //systemOper.edit_sys(driver,"cai-sys_daima","cai-sys_name1","/cai-sys_genlujin1","http://cai-shouye_dizhi1");

        /*业务设置(设置业务管理员)
        systemOper.set_owner(driver,"cai-sys_daima","业务管理员页面","cai1");
        systemOper.set_owner(driver,"cai-sys_daima","业务管理员页面","cai2");
        systemOper.set_owner(driver,"cai-sys_daima","权限管理员页面","cai1");
        systemOper.set_owner(driver,"cai-sys_daima","权限管理员页面","cai2");
        */

        /*删除管理员
        systemOper.delete_owner(driver,"cai-sys_daima","业务管理员页面","cai5");
        systemOper.delete_owner(driver,"cai-sys_daima","业务管理员页面","cai4");
        systemOper.delete_owner(driver,"cai-sys_daima","权限管理员页面","cai1");
        systemOper.delete_owner(driver,"cai-sys_daima","权限管理员页面","cai2");
        */
    }

    @Test
    public void quanxian_test() throws Exception{
        //权限管理页面
        Quanxian_Oper quanxianOper=new Quanxian_Oper();

        //差个导入权限和导出权限，这两以后再优化吧


        //进入权限管理
        quanxianOper.click_qxgl(driver);

        //选择配置权限的系统
        quanxianOper.choose_sys(driver,"cai-sys_name1");

        //打开开关
        quanxianOper.edit_kg(driver);

        //打开权限组织树并添加权限
        /*quanxianOper.open_tree(driver,"cai-sys_name1");
        quanxianOper.insert_qx(driver,"cai-sys_name1","a1-name","a1-value",0,"/caidan",0);
        quanxianOper.insert_qx(driver,"cai-sys_name1","a2-name","a2-value",1,"/angniu",1);
        quanxianOper.insert_qx(driver,"a1-name","b1-name","b1-value",2,"/caidan",2);
        quanxianOper.insert_qx(driver,"a1-name","b2-name","b2-value",1,"/angniu",3);

         */

        //编辑权限
        //quanxianOper.edit_qx(driver,"b1-name","n-b2-name","n-b2-value",0,"/caidan",2);

        //删除权限
        //quanxianOper.delete_qx(driver,"n-b2-name");


    }

    @Test
    public void role_test() throws Exception{
        //权限管理页面
        Role_Oper roleOper=new Role_Oper();

        //进入角色管理
        roleOper.click_jsgl(driver);

        //查询系统名+角色名+角色代码
       // roleOper.select_role(driver,"cai-sys_name1","role-name1","role-code1");
        //新增角色
        roleOper.insert_role(driver,"role-name1","role-code1");
        roleOper.insert_role(driver,"role-name2","role-code2");
        roleOper.insert_role(driver,"role-name3","role-code3");



        //查询系统名+角色名+角色代码
        roleOper.select_role(driver,"cai-sys_name1",null,null);
        //编辑角色
        roleOper.edit_role(driver,"role-code3","r-name3","r-code3");

        //删除角色
        //roleOper.delete_role(driver,"role-code2");

        roleOper.select_role(driver,"cai-sys_name1",null,null);
        //角色管理用户-新增和删除
        //roleOper.role_user_insert(driver,"rcode1","cai2");
        roleOper.role_user_delete(driver,"rcode1","cai2");

        //权限设置+导入角色+导入用户角色绑定关系+导入角色权限绑定关系：这四个页面暂时不做，后期优化

    }

    @Test
    public void user_test() throws Exception{
        //权限管理页面
        User_Oper userOper=new User_Oper();

        //进入用户管理
        userOper.click_yhgl(driver);

    }


}
