FROM node:20.12.2-buster

RUN npm install -g serve

COPY ./tmp/build/ /build/

CMD serve -s build