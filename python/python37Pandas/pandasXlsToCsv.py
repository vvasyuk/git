import pandas as pd

data_xls = pd.read_excel('c:/Users/jopa/Downloads/data/testData/levels/datapoint/Projects_Accounts_01252021.xls', 'Grid 1', dtype=str, index_col=None)
data_xls.to_csv('c:/Users/jopa/Downloads/data/testData/levels/datapoint/projects_20210125.csv', encoding='utf-8', index=False)