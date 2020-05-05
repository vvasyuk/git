from collections import defaultdict

transpose = {
    'A': ['¬C'],
    '¬C': ['B', '¬B'],
    'B': ['C'],
    '¬B': ['¬C', 'C'],
    'C': ['B', '¬A'],
}

def get_transpose(graph):
    transpose = defaultdict(list)

    for key, values in graph.items():
        for v in values:
            transpose[v].append(key)

    return transpose

res = get_transpose(transpose)
print(res)

x = res.get('C')
print(x)