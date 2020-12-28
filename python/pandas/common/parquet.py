import pandas as pd

def read():
    df = pd.read_parquet('D:/work/tryout/coursera/aws/glue/parquet/transformed/part-00028-67d87566-90b8-4676-8d6d-1e04a07b97a9.c000.snappy.parquet')
    return df