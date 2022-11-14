package com.litcode.server.grpcClient;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.litcode.server.dto.ProblemRunResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GrpcClient {

	private CodeRuntimeGrpc.CodeRuntimeBlockingStub pyStub;
	private CodeRuntimeGrpc.CodeRuntimeBlockingStub jsStub;

	@PostConstruct
	public void init() {
		// TODO
		// apply env var
		ManagedChannel pyChannel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
		ManagedChannel jsChannel = ManagedChannelBuilder.forAddress("localhost", 50052).usePlaintext().build();

		this.pyStub = CodeRuntimeGrpc.newBlockingStub(pyChannel);
		this.jsStub = CodeRuntimeGrpc.newBlockingStub(jsChannel);
	}

	public ProblemRunResponse runCode(String code, String input, String language) {

		RunRequest runReq = RunRequest.newBuilder().setCode(code).setInput(input).build();

		switch (language) {
			case "python":
				RunResponse pyRes = this.pyStub.runCode(runReq);
				return ProblemRunResponse.builder().stdout(pyRes.getStdout()).output(pyRes.getOutput()).build();

			case "javascript":
				try {
					RunResponse jsRes = this.jsStub.runCode(runReq);
					return ProblemRunResponse.builder().stdout(jsRes.getStdout()).output(jsRes.getOutput()).build();
				} catch (Exception e) {
					throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
				}

				// case "java":
				// return "notyet";

			default:
				// TODO
				// custom error handling
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT SUPPORTED LANGUAGE");
		}

	}
}
