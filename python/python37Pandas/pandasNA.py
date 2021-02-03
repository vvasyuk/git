import pandas as pd
s = pd.Series([1, 2, None], dtype="Int64")
print(s.to_string())
