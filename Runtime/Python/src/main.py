from concurrent import futures

import logging
import grpc
import codeRuntime_pb2
import codeRuntime_pb2_grpc


class CodeRuntime(codeRuntime_pb2_grpc.CodeRuntimeServicer):
    def RunCode(self, request, context):
        print(request)
        return codeRuntime_pb2.RunResponse(output="run")

    def SubmitCode(self, request, context):
        print(request)
        return codeRuntime_pb2.SubmitResponse("submit")


def serve():
    # 서버를 정의할 때, futures의 멀티 스레딩을 이용하여 서버를 가동할 수 있습니다.
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))

    # 위에서 정의한 서버를 지정해 줍니다.
    codeRuntime_pb2_grpc.add_CodeRuntimeServicer_to_server(CodeRuntime(), server)

    # 불안정한 포트 50051로 연결합니다.
    server.add_insecure_port('[::]:50051')
    server.start()
    server.wait_for_termination()


if __name__ == '__main__':
    logging.basicConfig()
    print('??')
    serve()
