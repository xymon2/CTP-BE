import json


def run(code, input):
    try:
        obj = json.loads(input)
        if isinstance(obj, str):
            obj = f'"{obj}"'

        template = f"""{code}
input = {obj}
res = solution(input)
"""
        print(template)
        answer = {}
        exec(template, {}, answer)
        return answer["res"]

    except Exception as e:
        print(e)
        return str(e)
