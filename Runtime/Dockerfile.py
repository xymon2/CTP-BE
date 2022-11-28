FROM python@sha256:45168bf789428a963f86a03da1039b1845d8bcb81d0d0ca031e4d6fc24394e72 AS builder

RUN apk add --update g++ build-base linux-headers libstdc++
COPY Python/src/main.py src/
COPY Python/src/run.py src/
COPY ./codeRuntime.proto ./
RUN pip3 install grpcio==1.44.0 protobuf==3.20.0 grpcio-tools==1.44.0
RUN python3 -m grpc.tools.protoc -I. --python_out=/src --grpc_python_out=/src codeRuntime.proto
CMD ["python3", "main.py"]