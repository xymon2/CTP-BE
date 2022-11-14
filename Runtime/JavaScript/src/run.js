function run(code, input, cb) {
  console.log = cb;
  const template = `
  try{
    ${code}
    return solution(${input});
  } catch(e){
    return e;
  }
  `;

  const runFunc = new Function(template);
  const ret = runFunc();
  return ret;
}

module.exports = { run };
