// GRPC setting
const PROTO_PATH = __dirname + "/../../codeRuntime.proto";
const grpc = require("@grpc/grpc-js");
const protoLoader = require("@grpc/proto-loader");
const packageDefinition = protoLoader.loadSync(PROTO_PATH, {
  keepCase: true,
  longs: String,
  enums: String,
  defaults: true,
  oneofs: true,
});
const codeRuntimeproto = grpc.loadPackageDefinition(packageDefinition);

// Import statements.
const winston = require("winston");
const { run } = require("./run");
const option = {
  levels: winston.config.npm.levels,
  level: "debug",
  format: winston.format.combine(
    winston.format.timestamp(),
    winston.format.metadata(),
    winston.format.colorize({ all: true }),
    winston.format.simple()
  ),
  transports: [
    new winston.transports.Console({
      silent: false,
    }),
    new winston.transports.File({
      filename: "./logs/logs.log",
    }),
  ],
};
const util = require("util");
const logger = winston.createLogger(option);

function runCode(call, callback) {
  logger.info("run start");
  const consolelogCb = console.log;
  try {
    const { code, input } = call.request;
    let stdOut = "";
    const stdOutCallback = (data) => {
      stdOut += util.format(data) + "\n";
    };
    const ret = run(code, input, stdOutCallback);
    callback(null, { output: JSON.stringify(ret), stdout: String(stdOut) });
    logger.info("run end");
  } catch (e) {
    callback({ message: e.message, code: 500 });
    logger.error(e);
  } finally {
    console.log = consolelogCb;
  }
}

function main() {
  var server = new grpc.Server();
  server.addService(codeRuntimeproto.CodeRuntime.service, {
    RunCode: runCode,
  });
  server.bindAsync(
    "0.0.0.0:50052",
    grpc.ServerCredentials.createInsecure(),
    () => {
      server.start();
    }
  );
  logger.info("grpc server start");
}

main();
