# node:16.10.0-alpine3.14
FROM node@sha256:4348589598e18251212266117f1b0af23ed3549bbf9392bbde8e2b1d1101f399 AS builder
WORKDIR /root
COPY JavaScript/src ./src
COPY JavaScript/package*.json ./
COPY ./codeRuntime.proto ./
RUN npm install
CMD ["node", "src/index.js"]