
docker run --name mysql8 -dp 3316:3306 --network-alias mysqlnwfence -v mysqldata:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_USER=inquiryuser -e MYSQL_PASSWORD=inquirypwd -e MYSQL_DATABASE=inquiry mysql:latest
docker build -t core-api-app .
docker run --rm --network fencenw -dp 11001:11001 --name core-server core-api-app