class Trie:
    def __init__(self, words=[]):
        self.trie = {}
        for word in words:
            self.add(word)

    def add(self, word):
        root = self.trie
        for letter in word:
            if letter in root:
                root = root[letter]
            else:
                root = root.setdefault(letter, {})
        root['#'] = '#'

    def get(self, prefix):
        root = self.trie
        for letter in prefix:
            if letter in root:
                root = root[letter]
            else:
                return None
        return root

#words = ["cat", "calf", "dog", "bear"]
words = ["bear"]

def is_winning(trie, prefix):
    root = trie.get(prefix)

    if '#' in root:
        return False
    else:
        next_moves = [prefix + letter for letter in root]
        print(next_moves)
        print(",")
        if any(is_winning(trie, move) for move in next_moves):
            return False
        else:
            return True

def optimal_starting_letters(words):
    trie = Trie(words)
    winners = []

    starts = trie.trie.keys()
    for letter in starts:
        if is_winning(trie, letter):
            winners.append(letter)

    return winners

print(optimal_starting_letters(words))
