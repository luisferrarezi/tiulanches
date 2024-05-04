#!/bin/bash

echo 'Subindo base dados...'
kubectl apply -f secrets.yaml
sleep 5
kubectl apply -f metrics.yaml
sleep 5
kubectl apply -f db-deployment.yaml

sleep 60
echo 'Subindo aplicação...'
kubectl apply -f tiulanches-deployment.yaml

https=http://localhost:31200/actuator/health
status=0
while [ $status -eq 0 ]
do
  sleep 5
  status=`curl $https -k -s -f -o /dev/null && echo 1 || echo 0`
done

echo 'Aplicação pronta, divirta-se ;)'