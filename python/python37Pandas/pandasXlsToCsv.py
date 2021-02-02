import pandas as pd

data_xls = pd.read_excel('c:/Users/jopa/Downloads/data/testData/levels/datapoint/Projects_02012021.xlsx', 'Grid 1', dtype=str, index_col=None)
data_xls.to_csv('c:/Users/jopa/Downloads/data/testData/levels/datapoint/projects_02012021.csv', encoding='utf-8', index=False)