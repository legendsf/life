
#打包命令
mvn clean package -U -DskipTests=true
#启动命令
java -jar -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.port=1899 -Dcom.sun.management.jmxremote.ssl=false -Xms1024m -Xmx4096m -Xmn512m -Xss512K -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m -XX:+UseCompressedOops -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly -XX:MaxTenuringThreshold=6 -XX:+ExplicitGCInvokesConcurrent -XX:+ParallelRefProcEnabled -Xloggc:/home/logs/HeapDump_Gc/${HOSTNAME}-gc.log -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCApplicationStoppedTime -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/logs/HeapDump_Gc/${HOSTNAME}.hprof -Dfile.encoding=UTF-8 -Duser.timezone=GMT+08 -Dspring.profiles.active=dohko /home/message-template-sender.jar
maven helper pom.xml 解决依赖以后一定要 reimport
