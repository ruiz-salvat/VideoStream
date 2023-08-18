# DEVELOPMENT

FROM node:18-alpine

WORKDIR /front-app

COPY ["package.json", "package-lock.json*", "./"]

RUN npm install

EXPOSE 3000

CMD npm install && npm run dev