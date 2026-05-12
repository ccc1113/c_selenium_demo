package demo2.Operator;

import demo2.Index_WebElement;
import org.openqa.selenium.*;

public class System_Oper {
    Index_WebElement c_index_ele;
    WebElement c_iele;
    WebElement c_element;
    int c_index;

    C_Operator c_oper=new C_Operator();


    public void click_xxgl(WebDriver driver) throws InterruptedException {
        //系统管理
        // 获取所有<div>元素 div[data-v-268e2540] .title 中间一定要有空格
        //进入系统管理
        System.out.println("进入系统管理");
        String a_system="li.el-menu-item > span";
        c_index_ele=c_oper.get_Element_index(driver,a_system,"系统管理","css");
        c_iele=c_index_ele.Element;
        c_iele.click();
    }


    public void insert_sys(WebDriver driver,String c_daima,String c_name,String c_genlujin,String c_dizhi) throws Exception {
        Thread.sleep(1000);
        //点击添加系统按钮
            //定位添加按钮的span没用，只能定位添加按钮的父级 li .el-icon-plus
        c_element=driver.findElement(By.cssSelector(".el-icon-plus"));
        //c_element=driver.findElement(By.xpath("//span[contains(text(), '添加系统')]"));
        //进入添加系统页面
        c_element.click();


        String sys_daima="//input[@class='el-input__inner' and @placeholder='请输入系统代码']";
        String sys_name="div.el-form-item__content > div.el-input--suffix > input[placeholder='请输入系统名称']"; //由于查询那边还有个系统名称，故需要更精确定位
        String sys_genlujin="//input[@class='el-input__inner' and @placeholder='请输入系统根路径']";
        String shouye_dizhi="input[placeholder='请输入首页地址']";
        //是否支持办公网访问+归属部门+业务负责人 均为非必填项，故暂不写入
        System.out.println("输入添加系统页面必填项：系统代码+系统名称+系统根目录+首页地址");
        driver.findElement(By.xpath(sys_daima)).sendKeys(c_daima);
        driver.findElement(By.cssSelector(sys_name)).sendKeys(c_name);
        driver.findElement(By.xpath(sys_genlujin)).sendKeys(c_genlujin);
        driver.findElement(By.cssSelector(shouye_dizhi)).sendKeys(c_dizhi);

        //点击创建+确定按钮
        c_element=driver.findElement(By.xpath("//span[contains(text(), '创建')]"));
        c_element.click();
        c_element=driver.findElement(By.xpath("//span[contains(text(), '确定')]"));
        c_element.click();
        //点击取消按钮
        //c_element=driver.findElement(By.xpath("//span[contains(text(), '取消')]"));
        //c_element.click();

    }

    public void edit_sys(WebDriver driver,String c_daima,String c_name,String c_genlujin,String c_dizhi) throws Exception {
        //查询需要修改的系统代码
        select_sys_name(driver,"系统代码",c_daima);

        Thread.sleep(1000);
        //点击编辑系统按钮
        c_element=driver.findElement(By.xpath("//span[contains(text(), '编辑') and @class='el-link--inner']"));
        //进入编辑系统页面
        c_element.click();

        String sys_name="div.el-form-item__content > div.el-input--suffix > input[placeholder='请输入系统名称']"; //由于查询那边还有个系统名称，故需要更精确定位
        String sys_genlujin="//input[@class='el-input__inner' and @placeholder='请输入系统根路径']";
        String shouye_dizhi="input[placeholder='请输入首页地址']";
        //是否支持办公网访问+归属部门+业务负责人 均为非必填项，故暂不写入
        System.out.println("输入编辑系统页面必填项：系统名称+系统根目录+首页地址");
        c_element=driver.findElement(By.cssSelector(sys_name));
        //先清除原有数据，再填充新数据
        c_element.clear();
        c_element.sendKeys(c_name);

        c_element=driver.findElement(By.xpath(sys_genlujin));
        //先清除原有数据，再填充新数据
        c_element.clear();
        c_element.sendKeys(c_genlujin);

        c_element=driver.findElement(By.cssSelector(shouye_dizhi));
        //先清除原有数据，再填充新数据
        c_element.clear();
        c_element.sendKeys(c_dizhi);

        //点击确定+二次确定按钮
        //c_element=driver.findElement(By.xpath("//span[contains(text(), '创建')]"));
        //c_element.click();
        c_element=driver.findElement(By.xpath("//span[contains(text(), '确定')]"));
        c_element.click();
        c_element=driver.findElement(By.xpath("//span[contains(text(), '      确定')]"));
        c_element.click();
        //点击取消按钮
        //c_element=driver.findElement(By.xpath("//span[contains(text(), '取消')]"));
        //c_element.click();

    }

    public void select_sys_name(WebDriver driver,String type,String c_name) throws Exception{
        Thread.sleep(1000);
        String select=null;
        if (type.equals("系统代码")){
            //点击选择-按系统名称/系统代码 查询
            c_element=driver.findElement(By.cssSelector("div.el-input--medium > input[placeholder='请选择']"));
            c_element.click();

            Thread.sleep(1000);

            //选择系统代码
            String choose="//span[contains(text(), '系统代码')]";
            c_element=driver.findElement(By.xpath(choose));
            c_element.click();

            select="div.el-row > div.system-input > input[placeholder='请输入系统代码']";

        } else if (type.equals("系统名称")) {
            //输入系统名称
            //当定位不到元素，但是的确存在时，可以考虑加个sleep
            select="div.el-row > div.system-input > input[placeholder='请输入系统名称']";
        }
        Thread.sleep(500);
        //定位查询框并传入查询值
        WebElement c_select=driver.findElement(By.cssSelector(select));
        c_select.clear();
        c_select.sendKeys(c_name);

        //点击查询按钮
        c_element=driver.findElement(By.xpath("//span[contains(text(),'查询')]"));
        c_element.click();

    }

    public String owner_ty(WebDriver driver,String c_daima,String type) throws Exception{
        //权限管理员/业务管理员  添加+删除 功能的通用部分
        //查询需要设置的系统
        select_sys_name(driver,"系统代码",c_daima);
        String close=null;
        Thread.sleep(1000);

        if (type.equals("业务管理员页面")){
            c_element=driver.findElement(By.xpath("//span[contains(text(), '业务设置') and @class='el-link--inner']"));
            //进入编辑业务管理员页面
            c_element.click();
            //关闭按钮定位：编辑业务管理员的下一个兄弟节点-button
            close="//span[contains(text(), '编辑业务管理员')]/following-sibling::button";

        } else if (type.equals("权限管理员页面")) {
            c_element=driver.findElement(By.xpath("//span[contains(text(), '权限设置') and @class='el-link--inner']"));
            //进入编辑权限管理员页面
            c_element.click();
            close="//span[contains(text(), '编辑权限管理员')]/following-sibling::button";
        }
        //返回不同的关闭按钮
        return close;
    }


    public void set_owner(WebDriver driver,String c_daima,String type,String name) throws Exception{
        //查询需要设置的系统 + 选择权限/业务管理员配置 +获取不同页面的关闭按钮路径
        String close=owner_ty(driver,c_daima,type);

        Thread.sleep(500);
        //传入想要添加的管理员账号后等待账号查询成功
        c_element=driver.findElement(By.xpath("//input[@placeholder='请输入要查询的账号']"));
        c_element.sendKeys(name);
        Thread.sleep(1500);

        //账号查询成功并选择
        // 不太好定位账号，所以直接用contains吧，可以包含账号信息
        driver.findElement(By.xpath("//li[contains(text(), '"+name+"')]")).click();

        Thread.sleep(500);
        //点击添加按钮+二次确定按钮
        //由于添加按钮有多个，上级的button的class也有多个，故用最上面的div.el-dialog__body
        c_element=driver.findElement(By.xpath("//div[@class='el-dialog__body']//span[contains(text(), '添加')]"));
        //需定位添加按钮的父级-button，来点击
        WebElement button=c_element.findElement(By.xpath(".."));
        System.out.println(button.getTagName()+button.getAttribute("class"));
        button.click();

        Thread.sleep(500);
        c_element=driver.findElement(By.xpath("//span[contains(text(), '   确定')]"));
        c_element.click();

        //关掉该页面
        c_element=driver.findElement(By.xpath(close));
        c_element.click();
    }

    public void delete_owner(WebDriver driver,String c_daima,String type,String name) throws Exception{
        //查询需要设置的系统 + 选择权限/业务管理员配置 +获取不同页面的关闭按钮路径
        String close=owner_ty(driver,c_daima,type);

        Thread.sleep(1500);
        //定位想要删除的管理员账号
        //text()=""代表精确匹配，contains(text(),"")是模糊匹配，因为contains代表模糊
        c_element=driver.findElement(By.xpath("//div[text()='"+name+"']"));
        //定位该账号对应的父级-td,再找它的父级-tr
        WebElement c_fuji1=c_element.findElement(By.xpath("../.."));
        //tr最下面的删除按钮(当前tr的第3个td-div-a-span删除)
        WebElement c_fuji2=c_fuji1.findElement(By.xpath("./td[3]//span[contains(text(),'删除')]"));
        Thread.sleep(500);
        c_fuji2.click();


        Thread.sleep(500);
        c_element=driver.findElement(By.xpath("//span[contains(text(), '   确定')]"));
        c_element.click();

        //关掉该页面
        c_element=driver.findElement(By.xpath(close));
        c_element.click();

    }

}
