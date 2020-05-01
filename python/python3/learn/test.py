def helper(root, seq, left, right):
    if root is None:
        return

    node = Node(seq[root])
    node.left = helper(left[root], seq, left, right)
    node.right = helper(right[root], seq, left, right)

    return node

def build_tree(seq):
    n = len(seq)
    parent, left, right = [None] * n, [None] * n, [None] * n

    root = 0
    for i in range(1, n):
        prev = i - 1

        while seq[i] < seq[prev] and prev != root:
            prev = parent[prev]

        if seq[i] < seq[prev]:
            left[i] = root
            parent[root] = i
            root = i

        else:
            if right[prev] is not None:
                parent[right[prev]] = i
                left[i] = right[prev]

            parent[i] = prev
            right[prev] = i

    return helper(root, seq, left, right)

build_tree([3, 2, 6, 1, 9])