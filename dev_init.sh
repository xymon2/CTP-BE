# DB run 
docker-compose -f DB/db.dev.yml up -d

# spring api server build
docker build -f API/Dockerfile -t litcode-api .
docker-compose -f API/api.dev.yml up -d
