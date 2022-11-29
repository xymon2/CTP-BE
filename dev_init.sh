# # DB run 
docker-compose -f DB/db.dev.yml up -d


# runtime js build
docker build -f Runtime/JavaScript/Dockerfile -t litcode-js .
docker compose -f Runtime/JavaScript/js.dev.yml up -d


# # spring api server build
docker build -f API/Dockerfile -t litcode-api .
docker-compose -f API/api.dev.yml up -d


  