# PRODUCTION

FROM node:18-alpine

WORKDIR /front-app

COPY ["front-app/package.json", "front-app/package-lock.json*", "./"]

RUN npm build

COPY front-app/ .

EXPOSE 3000

CMD npm run start