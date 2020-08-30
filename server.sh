
#!/bin/bash
# 应用名称:命令行输入的第一个参数
APP_NAME=$1
 
# 日志文件路径
LOG_NAME=./info.log
 
# Shell Info 
# 使用说明，用来提示输入参数
usage() {
    echo "Usage: sh run.sh [APP_NAME] [start|stop|restart|status]"
    exit 1
}
 
# 检查程序是否在运行
is_exist(){
        # 获取PID
        PID=$(ps -ef |grep ${APP_NAME} | grep -v $0 |grep -v grep |awk '{print $2}')
		
        # -z "${pid}"判断pid是否存在，如果不存在返回1，存在返回0
        if [ -z "${PID}" ]; then
                # 如果进程不存在返回1
                return 1
        else
                # 进程存在返回0
                return 0
        fi
}
 
# 定义启动程序函数
start(){
        is_exist
        if [ $? -eq "0" ]; then
                echo "${APP_NAME} is already running, PID=${PID}"
        else    
                nohup java -jar ${APP_NAME} >${LOG_NAME} 2>&1 &
                PID=$(echo $!)
                echo "${APP_NAME} start success, PID=$!"
        fi
}
 
# 停止进程函数
stop(){
        is_exist
        if [ $? -eq "0" ]; then
                # 强制关机
                kill -9 ${PID}
                # actuator提供的优雅关机:/actuator/shutdown,以下为自定义路径.TODO 存在问题:优雅关机需要一定时间,导致 restart 的时候启动失败
                #curl -X POST http://127.0.0.1:40000/MyActuator/shutdown
                echo "${APP_NAME} process stop, PID=${PID}"
        else    
                echo "There is not the process of ${APP_NAME}"
        fi
}
 
# 重启进程函数 
restart(){
        stop
        start
}
 
# 查看进程状态
status(){
        is_exist
        if [ $? -eq "0" ]; then
                echo "${APP_NAME} is running, PID=${PID}"
        else    
                echo "There is not the process of ${APP_NAME}"
        fi
}
 
case $2 in
"start")
        start
        ;;
"stop")
        stop
        ;;
"restart")
        restart
        ;;
"status")
       status
        ;;
	*)
	usage
	;;
esac
exit 0
