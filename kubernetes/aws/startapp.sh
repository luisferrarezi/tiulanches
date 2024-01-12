#!/bin/bash

echo 'Subindo base dados...'
kubectl apply -f secrets.yaml
kubectl apply -f metrics.yaml
kubectl apply -f db-deployment.yaml

echo 'Subindo aplicação...'
sleep 30
kubectl apply -f app-deployment.yaml