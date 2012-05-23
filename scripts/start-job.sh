#!/bin/bash
jar_name=""
host=""
user=""
main=""
args=""
while getopts "j:c:u:a:m:h" Option
do
	case $Option in
	j) jar_name=$OPTARG;;
	c) host=$OPTARG;;
	u) user=$OPTARG;;
	a) args=$OPTARG;;
	m) main=$OPTARG;;
	h)
	echo "Usage: $0 [-j jar] [-c cluster_address] [-u user_name] [-a args] [-m main_class_name]" 
	exit
	;;
	esac
done


if [ -z $jar_name ];then
	read -p "Jar name: " jar_name
fi

if [ -z $host ]; then
	read -p "Claster address: " host
fi

if [ -z $user ]; then
	read -p "Claster user: " user
fi

if [ -z $main ];then
	read -p "Main class name: " main
fi

if [ -z $args ];then
	read -p "Arguments: " args
fi

echo "hadoop/bin/hadoop jar hadoop/jars/$jar_name $main $args" | ssh $user@$host > out
echo $out
