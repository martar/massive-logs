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

source ./hadoop.cfg

if [ -z $address ];then
	read -p "Cluster address [$default_address] : " address
	if [ -z $address ]; then
	    address=$default_address
	fi
fi

if [ -z $user ];then
	read -p "Cluster user name [$default_user] : " user
	if [ -z $user ]; then
	    user=$default_user
	fi
fi

if [ -z $jar_file ];then
	read -p "jar file [$default_jar_file] : " jar_file
	if [ -z $jar_file ]; then
	    jar_file=$default_jar_file
	fi
fi

scp $jar_file $user@$address:~/hadoop/jars/

read -p "Start uploaded job? [y/N]" job_start

case $job_start in
"y" | "Y")
	if [ -z $main ];then
	    read -p "Main class name [$default_main]: " main
	    if [ -z $main ]; then
		main=$default_main
	    fi
	fi

	if [ -z $args ];then
	    read -p "Arguments [$default_args]: " args
	    if [ -z $args ]; then
		args=$default_args
	    fi
	fi
	echo "./start-job.sh -j $jar_file -c $address -u $user -m $main -a '$args'" | /bin/sh 
	echo $out
	;;
*)
;;
esac
