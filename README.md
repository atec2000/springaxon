```bash
cd ..
mvn clean install
DOCKER_HOST=unix:///var/run/docker.sock mvn docker:build  #Linux build image
(mvn docker:build #Windows build image)
cd docker
docker-compose up
docker-compose rm -v
```


```bash
$ curl -X POST -v --header "Content-Type: application/json" --header "Accept: */*" "http://127.0.0.1:8080/orders" -d '{"name":"order name 1", "lineItems":[{"name":"name 1","quantity":"3","unitPrice":"12.0"},{"name":"name 2","quantity":"4","unitPrice":"13.0"}]}'
```
