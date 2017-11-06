import xml.sax
import cx_Oracle

class Row:
    def __init__(self, id):
        self.col1 = id
        self.col2 = ' '
        self.col3 = ' '
        self.col4 = ' '
        self.col5 = ' '

    def getRow(self):
        return (self.col1, self.col2, self.col3, self.col4, self.col5)

class Handler(xml.sax.ContentHandler):
    def __init__(self):
        self.keeping_text = False
        self.saver = Saver();
        self.row = None
        self.buffer = ""

    def startElement(self, name, attrs):
        if name.lower() == 'book':
           self.row = Row(attrs["id"])
        if name.lower() == 'author':
            self.row.col2 = name
            self.keeping_text = True
        if name.lower() == 'price':
            self.row.col4 = name
            self.keeping_text = True

    def endElement(self, name):
        if name == "author":
            self.keeping_text = False
            self.author = self.buffer
        elif name == "price":
            self.keeping_text = False
            self.price = self.buffer
        elif name == "book":
            self.row.col3 = self.author
            self.row.col5 = self.price
            print self.row.getRow()
            self.saver.add(self.row.getRow())
            self.author = ""
            self.price = ""
        self.buffer = ""


    def characters(self, content):
        if self.keeping_text:
            self.buffer += content

    def endDocument(self):
        print('this is the end')
        self.saver.save();

class Saver():
    def __init__(self):
        self.elements = [];

    def save(self):
        print(self.elements.__len__())
        for i in self.elements:
            print i


    def add(self, t):
        self.elements.append(t)
        if self.elements.__len__() >= 10:
            self.elements[:] = []


parser = xml.sax.make_parser()
handler = Handler()
parser.setContentHandler(handler)
parser.parse(open("D:/work/python/parse_xml/book.xml","r"))