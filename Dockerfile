FROM ubuntu:latest
LABEL authors="decagon"

ENTRYPOINT ["top", "-b"]