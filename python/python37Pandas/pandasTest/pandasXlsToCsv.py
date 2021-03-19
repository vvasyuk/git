import pandas as pd

data_xls = pd.read_excel('C:/work/project/data/testData/users/sample_projects.xlsx', 'Sheet 3', dtype=str, index_col=None)
#print(data_xls.head(5).to_string())
data_xls.to_csv('C:/work/project/data/testData/users/users_20210319.csv', encoding='utf-8', index=False)



# xl = pd.ExcelFile('C:/work/project/data/testData/users/sample_projects.xlsx')
# print(xl.sheet_names)
# ['Sheet 1', 'SQL Statement', 'Sheet 3', 'Sheet4']

#xl.parse(sheet_name)  # read a specific sheet to DataFrame
