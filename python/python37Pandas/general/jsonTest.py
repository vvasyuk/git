import json

myStr = '{"vadym.vasiuk@deloitte2.com": "oiGRI6vLlFE52hj2vwAa"}'
loginPswd = [(k, v) for k, v in json.loads(myStr).items()]
print(loginPswd[0][0])
print(loginPswd[0][1])