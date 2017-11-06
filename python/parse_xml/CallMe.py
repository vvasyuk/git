class CallMe:
    def __init__(self):
        self.v1 = ' '
        self.v2 = ' '

    def getAll(self):
        return (self.v1, self.v2)

obj = CallMe()
print obj.getAll()

test = 'v1'

setattr(obj, test, 'magic')
print obj.getAll()