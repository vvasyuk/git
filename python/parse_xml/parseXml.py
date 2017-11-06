import xml.sax
import cx_Oracle

class Row:
    def __init__(self, col1):
        self.col1 = col1
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
        self.saver.closeCon()

class Saver():
    def __init__(self):
        self.elements = [];
        self.con = cx_Oracle.connect('test/12345678@127.0.0.1/XE')
        self.cur = self.con.cursor()

    def save(self):
        print(self.elements.__len__())
        self.cur.executemany("insert into a_mat2(COL1, COL2, COL3, COL4, COL5) values (:1, :2, :3, :4, :5)", self.elements)
        self.con.commit();

    def add(self, t):
        self.elements.append(t)
        if self.elements.__len__() >= 10:
            self.save();
            self.elements[:] = []

    def closeCon(self):
        self.cur.close()
        self.con.close()

parser = xml.sax.make_parser()
handler = Handler()
parser.setContentHandler(handler)
parser.parse(open("D:/work/python/parse_xml/book.xml","r"))

# <?xml version="1.0"?>
# <catalog>
#    <book id="bk101">
#       <author>Gambardella, Matthew</author>
#       <title>XML Developer's Guide</title>
#       <genre>Computer</genre>
#       <price>44.95</price>
#       <publish_date>2000-10-01</publish_date>
#       <description>An in-depth look at creating applications
#       with XML.</description>
#    </book>
#    <book id="bk102">
#       <author>Ralls, Kim</author>
#       <title>Midnight Rain</title>
#       <genre>Fantasy</genre>
#       <price>5.95</price>
#       <publish_date>2000-12-16</publish_date>
#       <description>A former architect battles corporate zombies,
#       an evil sorceress, and her own childhood to become queen
#       of the world.</description>
#    </book>
#    <book id="bk103">
#       <author>Corets, Eva</author>
#       <title>Maeve Ascendant</title>
#       <genre>Fantasy</genre>
#       <price>5.95</price>
#       <publish_date>2000-11-17</publish_date>
#       <description>After the collapse of a nanotechnology
#       society in England, the young survivors lay the
#       foundation for a new society.</description>
#    </book>
#    <book id="bk104">
#       <author>Corets, Eva</author>
#       <title>Oberon's Legacy</title>
#       <genre>Fantasy</genre>
#       <price>5.95</price>
#       <publish_date>2001-03-10</publish_date>
#       <description>In post-apocalypse England, the mysterious
#       agent known only as Oberon helps to create a new life
#       for the inhabitants of London. Sequel to Maeve
#       Ascendant.</description>
#    </book>
#    <book id="bk105">
#       <author>Corets, Eva</author>
#       <title>The Sundered Grail</title>
#       <genre>Fantasy</genre>
#       <price>5.95</price>
#       <publish_date>2001-09-10</publish_date>
#       <description>The two daughters of Maeve, half-sisters,
#       battle one another for control of England. Sequel to
#       Oberon's Legacy.</description>
#    </book>
#    <book id="bk106">
#       <author>Randall, Cynthia</author>
#       <title>Lover Birds</title>
#       <genre>Romance</genre>
#       <price>4.95</price>
#       <publish_date>2000-09-02</publish_date>
#       <description>When Carla meets Paul at an ornithology
#       conference, tempers fly as feathers get ruffled.</description>
#    </book>
#    <book id="bk107">
#       <author>Thurman, Paula</author>
#       <title>Splish Splash</title>
#       <genre>Romance</genre>
#       <price>4.95</price>
#       <publish_date>2000-11-02</publish_date>
#       <description>A deep sea diver finds true love twenty
#       thousand leagues beneath the sea.</description>
#    </book>
#    <book id="bk108">
#       <author>Knorr, Stefan</author>
#       <title>Creepy Crawlies</title>
#       <genre>Horror</genre>
#       <price>4.95</price>
#       <publish_date>2000-12-06</publish_date>
#       <description>An anthology of horror stories about roaches,
#       centipedes, scorpions  and other insects.</description>
#    </book>
#    <book id="bk109">
#       <author>Kress, Peter</author>
#       <title>Paradox Lost</title>
#       <genre>Science Fiction</genre>
#       <price>6.95</price>
#       <publish_date>2000-11-02</publish_date>
#       <description>After an inadvertant trip through a Heisenberg
#       Uncertainty Device, James Salway discovers the problems
#       of being quantum.</description>
#    </book>
#    <book id="bk110">
#       <author>O'Brien, Tim</author>
#       <title>Microsoft .NET: The Programming Bible</title>
#       <genre>Computer</genre>
#       <price>36.95</price>
#       <publish_date>2000-12-09</publish_date>
#       <description>Microsoft's .NET initiative is explored in
#       detail in this deep programmer's reference.</description>
#    </book>
#    <book id="bk111">
#       <author>O'Brien, Tim</author>
#       <title>MSXML3: A Comprehensive Guide</title>
#       <genre>Computer</genre>
#       <price>36.95</price>
#       <publish_date>2000-12-01</publish_date>
#       <description>The Microsoft MSXML3 parser is covered in
#       detail, with attention to XML DOM interfaces, XSLT processing,
#       SAX and more.</description>
#    </book>
#    <book id="bk112">
#       <author>Galos, Mike</author>
#       <title>Visual Studio 7: A Comprehensive Guide</title>
#       <genre>Computer</genre>
#       <price>49.95</price>
#       <publish_date>2001-04-16</publish_date>
#       <description>Microsoft Visual Studio 7 is explored in depth,
#       looking at how Visual Basic, Visual C++, C#, and ASP+ are
#       integrated into a comprehensive development
#       environment.</description>
#    </book>
# </catalog>