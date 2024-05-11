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

sleep 60
echo 'Aplicação pronta, divirta-se ;)'