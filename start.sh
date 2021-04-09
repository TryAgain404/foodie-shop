#!/usr/bin/env bash

killJava() {
  pid=`ps -ef|grep java|grep foodie|awk '{print $2}'`
  echo "foodie-shop pid is :$pdi"
  if [  "$pid" = "" ]
   then
     echo "no foodie-shop pid value"
   else
     kill -9 $pid
  fi
}

cd $PROJECTPATH
mvn clean install

killJava

rm -rf $APPPATH/logs
rm -rf $APPPATH/foodie-shop-api.jar

cp $PROJECTPATH/foodie-shop-api/target/foodie-shop-api.jar $APPPATH
cd $APPPATH

java -jar foodie-shop-api.jar