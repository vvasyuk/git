start = """<?xml version="1.0"?>
<catalog>
   <head id="bk101">
      <version>12345</version>
   </head>
"""

s = """    <book id="bk101">
      <author>Gambardella, Matthew</author>
      <title>XML Developer's Guide</title>
      <genre>Computer</genre>
      <price>44.95</price>
      <publish_date>2000-10-01</publish_date>
      <description>An in-depth look at creating applications</description>
      <subTrees>
         <subTree>
            <name>10n</name>
            <value>10v</value>
         </subTree>
         <subTree>
            <name>1010n</name>
            <value>1010v</value>
         </subTree>
      </subTrees>
    </book>
"""

end = """ </catalog>"""

# print(start)
# for x in range(0, 3):
#     print(s)
# print(end)

fo = open("book_test_003_003.xml", "w")

fo.write(start)
for x in range(0, 200000):
    fo.write(s)
fo.write(end)

# Close opend file
fo.close()