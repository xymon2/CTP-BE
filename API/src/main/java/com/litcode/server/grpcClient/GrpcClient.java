package com.litcode.server.grpcClient;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.litcode.server.dto.ProblemRunResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class GrpcClient {

	private CodeRuntimeGrpc.CodeRuntimeBlockingStub pyStub;

	@PostConstruct
	public void init() {
		// TODO
		// apply env var
		ManagedChannel pyChannel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();

		this.pyStub = CodeRuntimeGrpc.newBlockingStub(pyChannel);
	}

	public ProblemRunResponse runCode(String code, String input, String language) {

		RunRequest runReq = RunRequest.newBuilder().setCode(code).setInput(input).build();

		switch (language) {
			case "python":
				RunResponse runRes = this.pyStub.runCode(runReq);
				return ProblemRunResponse.builder().stdout(runRes.getStdout()).output(runRes.getOutput()).build();

			// case "javascript":
			// return "notyet";

			// case "java":
			// return "notyet";

			default:
				// TODO
				// custom error handling
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT SUPPORTED LANGUAGE");
		}

	}
}
