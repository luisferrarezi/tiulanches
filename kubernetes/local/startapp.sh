#!/bin/bash

echo 'Subindo base dados...'
kubectl apply -f secrets.yaml
sleep 5
kubectl apply -f metrics.yaml
sleep 5
kubectl apply -f db-deployment.yaml

echo 'Subindo aplicação...'
sleep 60
kubectl apply -f app-deployment.yaml