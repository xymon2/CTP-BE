import grpc
import pythonRuntime_pb2
import pythonRuntime_pb2_grpc
import logging


def run():
    # grpc의 불안정한 채널인 위에서 오픈한 50051 포트로 연결합니다.
    with grpc.insecure_channel('localhost:50051') as channel:
        # stub을 생성해줍니다.
        stub = pythonRuntime_pb2_grpc.CodingTestStub(channel)

        # 요청을 보내고 결과를 받는데, 서버에서 지정한 메서드에 요청시 사용할 proto 메시지 형식으로 요청을 전송합니다.
        response = stub.RunCode(pythonRuntime_pb2.RunRequest(code="code", input="inpyt"))
    print("client received: " + response.output)


if __name__ == '__main__':
    logging.basicConfig()
    run()
