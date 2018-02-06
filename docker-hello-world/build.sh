#!/bin/bash

job_name="hello"
registry="registry.zeasn.local:5000/apps"

version="1.0"

now=$(docker images | grep ${registry}/${job_name} |awk '{print $2}' | sort -nr -k 2 | head -n 1)
new=$(echo ${now} + 0.01 | bc)

tag=${registry}/${job_name}:${new}

#执行
cp $WORKSPACE/target/hello.war ${WORKSPACE}
docker build -t ${tag} ${WORKSPACE}/.
docker push ${tag}