# DEVELOPMENT

FROM nginx

RUN rm /etc/nginx/conf.d/default.conf

COPY default.conf /etc/nginx/conf.d

WORKDIR /usr/share/nginx/html

RUN rm -rf ./*

COPY ./src ./

ENTRYPOINT ["nginx", "-g", "daemon off;"]