#!/bin/bash

while getopts "c:u:j:h" Option
do
	case $Option in
	c) address=$OPTARG;;
	u) user=$OPTARG;;
	j) jar_file=$OPTARG;;
	h) 
	echo "Usage: $0 [-c cluster_address] [-u user_name] [-j jar_file]"
	exit
	;;
	esac
done

if [ -z $address ];then
	read -p "Cluster address: " address
fi

if [ -z $user ];then
	read -p "Cluster user name: " user
fi

if [ -z $jar_file ];then
	read -p "jar file: " jar_file
fi

scp $jar_file $user@$address:~/hadoop/jars/

#address=192.168.1.103
#user=hadoop-user
#jar_file=logs.jar

read -p "Start uploaded job? [y/N]" job_start

case $job_start in
"y" | "Y")
	read -p "Main class: "	main
	read -p "Arguments: " args
	echo "./start-job.sh -j $jar_file -h $address -u $user -m $main -a '$args'" | /bin/sh > out
	echo $out
	;;
*)
;;
esac


