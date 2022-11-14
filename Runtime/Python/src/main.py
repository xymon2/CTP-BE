from concurrent import futures

import logging
from io import StringIO
from contextlib import redirect_stdout
import json
import grpc
import codeRuntime_pb2
import codeRuntime_pb2_grpc
from run import run

logger = logging.getLogger("Python-Runtime")

class CodeRuntime(codeRuntime_pb2_grpc.CodeRuntimeServicer):
    def RunCode(self, request, context):
        logger.info("run codes")
        lines = request.code.splitlines()
        code_lines = ""
        for line in lines:
            code_lines += line+'\n'
            
        obj = json.loads(request.input)

        # store stdouts(print) and return value
        str_io = StringIO()
        with redirect_stdout(str_io):
            ret = run(code_lines, obj)
        logger.info(f"return value:{ret}")

        return codeRuntime_pb2.RunResponse(output=str(ret), stdout=str_io.getvalue())

    def SubmitCode(self, request, context):
        print(request)
        return codeRuntime_pb2.SubmitResponse("submit")


def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    codeRuntime_pb2_grpc.add_CodeRuntimeServicer_to_server(CodeRuntime(), server)

    server.add_insecure_port('localhost:50051')
    server.start()
    server.wait_for_termination()


if __name__ == '__main__':
    logging.basicConfig(format='%(levelname)s:%(name)s [%(asctime)s] %(message)s',level=logging.INFO,datefmt='%Y-%m-%d %H:%M:%S')
    logger.info("grpc server start")
    serve()