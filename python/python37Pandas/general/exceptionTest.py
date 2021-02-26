
def thrower(i):
    result={}
    result['error'] = "aaa"
    if i<1:
        print("ok")
    else:
        raise Exception(f"exception: {result['error']}")

thrower(0)
try:
    thrower(1)
except Exception as e:
    print(e)