# PRODUCTION

FROM nginx

RUN rm /etc/nginx/conf.d/default.conf

COPY default.conf /etc/nginx/conf.d

WORKDIR /usr/share/nginx/html

RUN rm -rf ./*

COPY ./src/error502.html ./error502.html

ENTRYPOINT ["nginx", "-g", "daemon off;"]