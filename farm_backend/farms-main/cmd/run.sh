#! /bin/sh 


env_opt=`cat ../../cmd/env.cfg`

# 因为stopall.sh命令里面是根据jar包名字去kill进程的，所以这里jar包名字不能跟其他模块相同，可以考虑优化stopall脚本
jvmline=`cat env-${env_opt}.cfg | grep jvmopts`

#对IFS分隔符变量 进行替换处理
OLD_IFS="$IFS"
IFS="~"
jvmopts=(${jvmline})

# 恢复shell默认分割符配置
IFS="$OLD_IFS"

java ${jvmopts[1]} -jar -Dspring.profiles.active=${env_opt}  attendancemain-3.8.5.jar 
