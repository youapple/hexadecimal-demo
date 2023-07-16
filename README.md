# hexadecimal-demo

#### 项目介绍

hexadecimal-demo项目是公众号《十六进制说》的示例项目源代码，可公开使用。  

#### 软件架构

编辑器: IntelliJ IDEA 2022.1  
spring boot: 3.1  
jdk: 17+  
maven: 3.5.4+  

#### 安装教程

1. git clone https://github.com/youapple/hexadecimal-demo.git
2. 切换到具体工程目录，例如springboot3-mybatisplus，选择pom.xml，右键Add as Maven Project。为保证依赖包下载速度，请修改maven setting文件，配置阿里云中央仓库，具体操作自行谷歌
3. 修改工程的jdk版本，File->Project Structure, 分别点击Project Settings->Project, Project Settings->Modules修改JDK版本为17+

#### 使用说明

程序运行入口: ExampleApplication，示例工程：springboot3-example  

--已完成  
1. springboot3-mybatisplus：整合mybatis-plus  
2. springboot3-druid：整合Druid，使用druid-spring-boot-3-starter集成，目前除了数据源能加载成功外，其余配置项采用默认的配置，自定义配置无法生效。  
   springboot3-druid-2：整合Druid，使用原生方式集成，除druid控制台无法打开外，其余均可正常使用。
3. springboot3-jpa：整合jpa  
4. springboot3-dynamic-datasource：整合多数据源(Dynamic DataSource)  
5. springboot3-mail：整合mail  
6. springboot3-swagger：整合swagger  
7. springboot3-knife4j：整合knife4j  
8. springboot3-dubbo: 整合dubbo  
9. springboot3-redis: 整合redis  

--待排期  
10. springboot3-nacos: 整合nacos    
11. springboot3-rabbitmq: 整合rabbitmq  
12. springboot3-kafka: 整合kafka  
13. springboot3-rocketmq: 整合rocketmq  
14. springboot3-quartz: 整合quartz  
15. springboot3-xxl-job: 整合xxl-job  
16. springboot3-mongodb: 整合mongodb  
17. springboot3-shiro: 整合shiro  
18. springboot3-security: 整合security  
19. springboot3-jwt: 整合jwt  
20. springboot3-websocket: 整合websocket  
21. springboot3-docker: docker发布  
22. springboot3-elasticsearch: 整合elasticsearch  
23. springboot3-minio: 整合minio  
24. springboot3-shardingjdbc: 整合shardingjdbc  
25. springboot3-seata: 整合seata  
26. springboot3-cas: 整合cas  
27. springboot3-ldap: 整合ldap  
28. springboot3-oauth: 整合oauth  

#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

#### 其他
1. 如果本项目对你帮助，请帮忙star一下。
2. 如有疑问，可以通过issues或者公众号反馈给我。

> 关注我的公众号：十六进制说    
![十六进制说](qrcode_hexadecimal.jpg)  
