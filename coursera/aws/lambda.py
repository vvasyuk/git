import boto3
from wdap.client import WDAPClient
import pandas as pd
from io import BytesIO
from time import gmtime, strftime
import pyarrow.parquet as pq
import awswrangler as wr

print('Loading function')

def lambda_handler(event, context):
    df = wr.s3.read_parquet(path='s3://glue-demo-bucket-vasiuk/transform/partition_dt=20210102/')
    print(df.to_string)