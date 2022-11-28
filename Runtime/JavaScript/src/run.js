function run(code, input, cb) {
  console.log = cb;
  const template = `${code}
    return solution(${input});
  `;

  try {
    const runFunc = new Function(template);
    const ret = runFunc();
    return ret;
  } catch (e) {
    return e.message;
  }
}

module.exports = { run };
