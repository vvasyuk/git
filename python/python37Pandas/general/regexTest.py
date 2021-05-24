import re
import json

err = [{
    "id": "36168870019833250587423906632850888362829201478393135110",
    "timestamp": 1621868224485,
    "message": "{'ERROR': 'Parquet magic bytes not found in footer. Either the file is corrupted or this is not a parquet file.', 'entity_name': 'MyStateMachine', 'retry_current': '1', 'retry_total_count': '3', 'retry_delay': '15'}\n"
}, {
    "id": "36168870019877852077820967879133959799374498201405095943",
    "timestamp": 1621868224487,
    "message": "[ERROR] ArrowInvalid: Parquet magic bytes not found in footer. Either the file is corrupted or this is not a parquet file.\nTraceback (most recent call last):\n\u00a0\u00a0File \"/var/task/lambda_function.py\", line 101, in lambda_handler\n\u00a0\u00a0\u00a0\u00a0raise e\n\u00a0\u00a0File \"/var/task/lambda_function.py\", line 47, in lambda_handler\n\u00a0\u00a0\u00a0\u00a0res = func_which_fails(event)\n\u00a0\u00a0File \"/var/task/lambda_function.py\", line 126, in func_which_fails\n\u00a0\u00a0\u00a0\u00a0insert_data(\"insert_standard_data\", \"\", files, wdap, use_parallel=\"true\", use_mapping_opt=True)\n\u00a0\u00a0File \"/var/task/lambda_function.py\", line 824, in insert_data\n\u00a0\u00a0\u00a0\u00a0df = wr.s3.read_parquet(path=file)\n\u00a0\u00a0File \"/opt/python/awswrangler/s3/_read_parquet.py\", line 610, in read_parquet\n\u00a0\u00a0\u00a0\u00a0return _union(dfs=[_read_parquet(path=p, **args) for p in paths], ignore_index=None)\n\u00a0\u00a0File \"/opt/python/awswrangler/s3/_read_parquet.py\", line 610, in <listcomp>\n\u00a0\u00a0\u00a0\u00a0return _union(dfs=[_read_parquet(path=p, **args) for p in paths], ignore_index=None)\n\u00a0\u00a0File \"/opt/python/awswrangler/s3/_read_parquet.py\", line 411, in _read_parquet\n\u00a0\u00a0\u00a0\u00a0table=_read_parquet_file(\n\u00a0\u00a0File \"/opt/python/awswrangler/s3/_read_parquet.py\", line 365, in _read_parquet_file\n\u00a0\u00a0\u00a0\u00a0pq_file: Optional[pyarrow.parquet.ParquetFile] = _pyarrow_parquet_file_wrapper(\n\u00a0\u00a0File \"/opt/python/awswrangler/s3/_read_parquet.py\", line 38, in _pyarrow_parquet_file_wrapper\n\u00a0\u00a0\u00a0\u00a0return pyarrow.parquet.ParquetFile(source=source, read_dictionary=read_dictionary)\n\u00a0\u00a0File \"/opt/python/pyarrow/parquet.py\", line 199, in __init__\n\u00a0\u00a0\u00a0\u00a0self.reader.open(source, use_memory_map=memory_map,\n\u00a0\u00a0File \"pyarrow/_parquet.pyx\", line 1021, in pyarrow._parquet.ParquetReader.open\n\u00a0\u00a0File \"pyarrow/error.pxi\", line 84, in pyarrow.lib.check_status"
}]



mystr = err[0]['message']
d = json.loads(str(mystr).replace("'","\""))

print(d['ERROR'])
print(d['entity_name'])
print(d['retry_current'])
print(d['retry_total_count'])
print(d['retry_delay'])
#
# result = re.findall(r'entity_name - (.*), retry_current', mystr)
# print(result[0])

#print({'ERROR': "file {e} does not exist. Probably that is OK but you need to check."})






# try:
#     raise Exception("Sorry, no numbers below zero")
# except Exception as e:
#     print(f"ERROR : caller - {map['key']}. message: {e}")
    #raise e
#print(f"issue is with {map['key']}")