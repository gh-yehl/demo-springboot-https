Demo - Integrate Springboot with HTTPS  

1 - 依赖  
    
        <parent>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-parent</artifactId>
            <version>2.0.1.RELEASE</version>
            <relativePath/> <!-- lookup parent from repository -->
        </parent>
    
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
        </dependencies>

2 - application.properties - 启用HTTPS配置  

        server.port=8443
        server.ssl.protocol=TLS
        server.ssl.key-store=classpath:keystore.httpskey
        server.ssl.key-store-password=123456
        server.ssl.key-store-type=JKS
        #keytool -genkey -alias httpskey -storetype JKS -keyalg RSA -keysize 2048 -keystore d:\keystore.httpskey -validity 365
        #keytool -genkey -alias httpskey -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore d:\keystore.p12 -validity 365

3 - Java keytool 生成测试 key  

        1) 到JDK bin目录
        2) 执行 keytool -genkey -alias httpskey -storetype JKS -keyalg RSA -keysize 2048 -keystore d:\keystore.httpskey -validity 365

4 - copy d:\keystore.httpskey 到 application.properties file 目录  
    
    tips: 本地测试，可能需要手动将 d:\keystore.httpskey copy到 编译的文件的 classes目录下
    
5 - 启动即可用  

6 - 同时启用 http(springboot 默认在启用https后, http会变不可用)  
    
        @Bean
        public ServletWebServerFactory servletContainer() {
            TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
            tomcat.addAdditionalTomcatConnectors(createStandardConnector());
            return tomcat;
        }
    
        private Connector createStandardConnector() {
            Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
            connector.setPort(8080);
            return connector;
        }