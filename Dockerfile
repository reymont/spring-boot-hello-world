FROM frolvlad/alpine-oraclejdk8

MAINTAINER reymont 258227346@qq.com

add spring-boot-hello-world-1.0-SNAPSHOT.jar ./jmx-hello.jar

CMD java -Dcom.sun.management.jmxremote \
  -Dcom.sun.management.jmxremote.authenticate=false \
  -Dcom.sun.management.jmxremote.ssl=false \
  -Dcom.sun.management.jmxremote.port=10001 \
  -jar jmx-hello.jar