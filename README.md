cd ..
mvn clean install
DOCKER_HOST=unix:///var/run/docker.sock mvn docker:build
cd docker
docker-compose up
docker-compose rm -v

curl -H "Content-Type: application/json" -X POSTz","rawContent":"xyz","publicSlug": "publicslug","draft": true,"broadcast": true,"category": "ENGINEERING", "publishAt": "2017-12-23T14:30:00+00:00"}' http://127.0.0.1:8080/blogpostcommands
