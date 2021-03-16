import pandas as pd

df = pd.read_parquet('c:/work/project/data/raw/wdap/levels/latest/')
print(df.head(3).to_string())