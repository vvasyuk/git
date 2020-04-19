import hashlib

array = [False] * 1000

def get_hash(f):
    def hash_function(value):
        h = f(str(value).encode('utf-8')).hexdigest()
        return int(h, 16) % len(array)

    return hash_function

hash_algorithms = [
            hashlib.md5,
            hashlib.sha1,
            hashlib.sha256,
            hashlib.sha384,
            hashlib.sha512
        ]
hashes = [get_hash(f) for f in hash_algorithms[:3]]


value=13
for h in hashes:
    v = h(value)
    array[v] = True

for h in hashes:
    v = h(value)
    if not array[v]:
        print(False)