#!/bin/bash

source ./hadoop.cfg

while getopts "j:c:u:a:m:h" Option
do
	case $Option in
	j) jar_file=$OPTARG;;
	c) host=$OPTARG;;
	u) user=$OPTARG;;
	a) args=$OPTARG;;
	m) main=$OPTARG;;
	h) echo "Usage: $0 [-j jar] [-c cluster_address] [-u user_name] [-a args] [-m main_class_name]" 
	exit
	;;
	esac
done



if [ -z $host ]; then
	read -p "Claster address [$default_address] : " host
	if [ -z $user ]; then
	    host=$default_address
	fi
fi

if [ -z $user ]; then
	read -p "Cluster user name [$default_user] : " user
	if [ -z $user ]; then
	    user=$default_user
	fi
fi

if [ -z $jar_file ]; then
	read -p "jar file [$default_jar_file] : " jar_file
	if [ -z $jar_file ]; then
	    jar_file=$default_jar_file
	fi
fi

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

echo "hadoop/bin/hadoop jar hadoop/jars/$jar_file $main $args" | ssh $user@$host > out
echo $out
