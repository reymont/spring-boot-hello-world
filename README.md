# Sample Spring Boot Application #

This sample Spring Boot application was written for a blog post describing how to create a Spring Boot application with IntelliJ.

http://patrickgrimard.com/2014/08/14/how-to-build-a-spring-boot-application-using-intellij-idea/

#ADD Dockerfile demo
```
mvm clean package
cd target
docker build -t jmx-hello .
docker run -d -p 10001:10001 --restart=always --name=jh jmx-hello
```

You can use https://github.com/reymont/jvmviewer to get information.

```
http://<jolokia>:8080/jvmviewer/jolokia.html?ip=<jmx_server>&port=10001
```


# aop

* 正常执行
  * `http://localhost:8080/first`
* 抛出异常
  * `http://localhost:8080/doError`
* 带参数
  * `http://172.20.54.95:8080/hello?name=reymont`