import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
  vus: 10000,
  duration: '3s',
};

export default function () {
  http.get('http://localhost:31200/actuator/health');
  sleep(1);
}
