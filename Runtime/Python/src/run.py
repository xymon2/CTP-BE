def run(code, input):
    template = f"""
try:
    {code}   
    input = {input}
    res = solution(input)

except Exception as e:
    res = e
"""

    answer = {}
    exec(template,{},answer)

    return answer["res"]