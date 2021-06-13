## **学习方法**
- 读官方文档的范例，在 pom.xml 中添加依赖
- 前端框架直接拷贝进 static，并在页面中引用

<br>

## **技术栈**
|技术|概念|
|:-:|:-:|
|**Spring Boot**|简化 Spring 应用配置的框架，内置 Tomcat|
|**Thymeleaf**|模板引擎，可取代 jsp 进行数据渲染|
|**Bootstrap**|最简单的前端框架，响应式布局：栅格系统|

<br>

产品 / 业务规划界面
交互设计师出平面图
开发

<br>

## **项目结构**
- **java**：java 代码
- **resources**：静态文件及配置文件
  - **static**：image, css 及 js 文件
  - **templates**：模板文件
  - **application.properties**：配置 Spring

<br>

## **IDEA 使用技巧**
|快捷键|功能|
|:-:|:-:|
|Ctrl + P|查看函数参数|
|Ctrl + Shift + N|查找|
|Shift + F6|重命名|

<br>

## **Git 命令**
|命令|功能|
|:-:|:-:|
|git add .|提交新文件和被修改的文件到暂存区|
|git status|查看上次提交之后文件是否修改|
|git commit -m ""|提交暂存区到本地仓库中，-m 为描述信息|
|git commit --amend --no-edit|追加到上次提交中，同时不修改 commit 描述|
|git push|推送到远程仓库|


<br>

## **开发流程**
### **1. 明确需求**
将开源项目功能拆解

1. 登录
2. 标签列表
3. 话题列表
4. 热门话题
5. 热门用户

<br>

### **Thymeleaf：如何获取前端传送的参数？**
例：https://localhost:8887/hello?name=qzf

1. 新建 `controller` 目录，存放 `HelloController` 类
2. 类前添加 ``@Controller` 将类识别为 `Controller`
3. 通过 *Thymeleaf* 从前端获取参数
   1. `HelloController` 类获取前端传送的 `name` 参数
   2. 将 `name` 参数承载到 `Model` 实例中
   3. `return "hello"` 到 *templates* 目录下搜索同名模板，将 `name` 显示到页面中
4. 在 *application.properties* 中修改 `server.port`
5. 运行 `CommunityApplication`，启动 *Spring* 应用

```
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
}
```

<br>

### **Bootstrap：栅格系统，响应式布局**

1. 下载 *Bootstrap* 包，将资源文件复制到 `resources/static` 文件夹中
2. 在主页 `index.html` 中引入资源文件
```
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="css/bootstrap-theme.min.css" />
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="js/bootstrap.min.js" type="application/javascript"></script>
```
3. 在 *Bootstrap* 组件中找到**导航条**，复制代码至页面
4. 根据需求删除无用组件，修改组件的显示信息

<br>

### **2. 实现功能**

#### **2.1. 登录功能**

<br>

## **踩坑记录**
**Error 1**：Circular view path [hello]: would dispatch back to the current handler URL
**Solution**：依赖下载不完全
