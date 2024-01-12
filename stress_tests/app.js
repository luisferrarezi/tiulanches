import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
  vus: 10000,
  duration: '3s',
};

export default function () {
  http.get('http://a8526fc1daf1a4093880d91d27f3e915-1834186291.us-east-1.elb.amazonaws.com:8080/actuator/health');
  sleep(1);
}
