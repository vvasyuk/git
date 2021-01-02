import boto3
from wdap.client import WDAPClient
import pandas as pd
from io import BytesIO
from time import gmtime, strftime

print('Loading function')

s3 = boto3.client("s3")

def lambda_handler(event, context):
    #wdap = WDAPClient(login='vadym.vasiuk@infopulse.com', password='oiGRI6vLlFE52hj2vwAa')
    #result = wdap.get_users(groups=True, return_response=False)
    buffer = BytesIO()
    object = s3.Object('glue-demo-bucket-vasiuk', 'master/datapoint/*')
    object.download_fileobj(buffer)
    
    df = pd.read_parquet(buffer)
    print(df.to_string)