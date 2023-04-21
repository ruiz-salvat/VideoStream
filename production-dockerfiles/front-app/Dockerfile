FROM node:18-alpine

WORKDIR /front-app

COPY ["../../front-app/package.json", "../../front-app/package-lock.json*", "./"]

RUN npm install

COPY ../../front-app/. .

EXPOSE 3000

CMD npm run dev