# from common.print_lol import *
from common.parquet import read

# def f1(str):
#     print_lol(['hi','there'])
#     return str.upper()

def f2():
    df = read()
    print(df)

def main():
    # print(f1("test"))
    f2()
    print("hi")


if __name__ == '__main__':
    main()