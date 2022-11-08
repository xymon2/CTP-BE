package com.litcode.server.grpcClient;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class GrpcClient {

	private CodeRuntimeGrpc.CodeRuntimeBlockingStub stub;

	@PostConstruct
	public void init() {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();

		this.stub = CodeRuntimeGrpc.newBlockingStub(channel);
	}

	public void test() {

		RunRequest runReq = RunRequest.newBuilder().setCode("code1").setInput("input1").build();
		RunResponse runRes = this.stub.runCode(runReq);

		System.out.println(runRes.getOutput());
	}
}
