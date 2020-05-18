from collections import defaultdict

preferences = {[1, 7, 3], [2, 1, 6, 7, 9], [3, 9, 5]}
songs = set([song for user in preferences for song in user])
print(songs)