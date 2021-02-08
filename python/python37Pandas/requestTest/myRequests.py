import requests

def f1(str):
    # r1 = requests.get('https://api.github.com/events')
    # print(r1.headers)

    payload = {'key1': str}
    r2 = requests.post("https://httpbin.org/post", data=payload)
    print(r2.text)

def main():
    print(f1("test"))

if __name__ == '__main__':
    main()
