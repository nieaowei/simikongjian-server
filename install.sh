#!/bin/bash
res=""
echo "The script is running."
echo "The docker installer is running."
if res=`docker --version | grep "Docker"`
then
	echo "The Docker is installed."
	echo "Your Docker vesion is $res"
elif	curl -fsSL https://get.docker.com | bash -s docker --mirror Aliyun
then
	echo "The Docker install is sucessed."
else
	echo "The Docker install is faild."
	exit 1
fi

if wget https://raw.githubusercontent.com/nieaowei/Express-cabinet-access-system/master/Java/daemon.json
then
	echo "The dir is made."
else
	echo "The dir is to make faild."
	exit 3
fi

if mv daemon.json /etc/docker/daemon.json
then
	echo "The dir is made."
else
	echo "The dir is to make faild."
	exit 3
fi

if systemctl status docker | grep "running"
then
	echo "The Docker is running."
elif systemctl start docker
then
	echo "The Docker is started."
else
	echo "The Docker start is faild."
	exit 2
fi

if systemctl enable docker
then
	echo "The Docker autonal starting is set."
else
	echo "The Docker autonal start seting is faild."
fi


if docker pull mysql:5.7.28
then
	echo "The image of mysql is pull."
else
	echo "The image of mysql pulling is faild.Your network is probrably faild."
	exit 3
fi

if wget https://raw.githubusercontent.com/nieaowei/simikongjian-server/master/mysqldata.tgz
then
	echo "The dir is made."
else
	echo "The dir is to make faild."
	exit 3
fi


if tar zxvf mysqldata.tgz -C /usr/local
then
	echo "The dir is made."
else
	echo "The dir is to make faild."
	exit 3
fi

if docker run --name mysql5728 --net=host -v /usr/local/mysqldata:/var/lib/mysql -d mysql:5.7.28
then
	echo "The image of mysql is running."
else
	echo "The image of mysql running is faild."
	exit 4
fi


if docker pull nieaowei/remote-tomcat-deploy
then
	echo "The image is pull."
else
	echo "The image pulling is faild.Your network is probrably faild."
	exit 3
fi

if docker run --name tomcat --net=host -d nieaowei/remote-tomcat-deploy
then
	echo "The images is running."
else
	echo "The images running is faild."
	exit 4
fi