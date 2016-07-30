* pom项目中各目录说明：
	1. Servers/tomcat7目录
		1.1. 本目录存放的是tomcat7配置文件，请把你workspace中tomcat 7服务器的配置文件指向本目录
		1.2. 用户认证信息在tomcat-users.xml中
		1.3. JNDI数据源及JMS连接池在context.xml中，根据项目开发环境对设置进行相应修改。如果你不需要用到JMS，请注释掉它。
		1.4. 其它文件未有特殊修改
		
	2. Servers/jetty7目录
		本目录存放的是jetty 7.x以上的plugin配置文件，根据项目开发环境对设置进行相应修改。如果你不需要用到JMS，请注释掉它。
		
	3. mvn目录
		settings.xml是maven配置文件，请复制到你个人目录下的.m2目录中，并修改<localRepository>到你电脑中合适的目录，
			用户名和密码更改成你的sametime用户和密码。
			
	4. eclipse目录
		4.1. 本目录存放的是code template, code format template及checktyle template。请导入到你的eclipse中。
		4.2. 为了保证格式化效果一致，请打开"window -> preferences"，在"filter text"中输入"line"，然后一个个的把"line width"由72改成150.
		
	5. vars目录详见目录下的readme.txt

* 开启JMS样例：
	1. 	service项目：
		1.1  开启类：SampleListener, SampleSender, JmsSenderTest, JmsReceiverTest
		1.2 pom.xml中开启JMS相关的dependency
		1.3 sample-res.xml 中开启相关的JNDI
		1.4 sample-res-test.xml 中开启相关的测试用连接工厂配置
	2. web-in项目：
		2.1 web.xml中，开启相应的sample-jms.xml