# Instrumentation-usage
![image](https://img.shields.io/badge/jdk-11-brightgreen.svg)

## 该工程主要用于java instrument的实践
## debugger
>用idea启动时，在jvm参数中添加-javaagent:E:/study/Instrumentation-usage/agent/target/agent.jar

>debugger运行即可进入调试

## org.dq.transformer.PreMain 

用于main方法启动之前，目标项目通过jvm参数中添加-javaagent:${your path}/agent.jar

调试直接用idea的debug方式即可调试

## org.dq.transformer.AgentMain

### 用于main方法运行中时调用
    1. cd ${agent path}
    2. jps 查看目标线程的id(pid)
    3. java -jar agent.jar 18683 org.dq.Test （18683 -> pid,org.dq.Test需要修改的类）
    4. 调试可以通过在启动时配置参数-Xdebug -Xrunjdwp:transport=dt_socket,address=5005,server=y,suspend=y
测试后调试只能调试main方法里面的内容，不能调试transform里面的内容