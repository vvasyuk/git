def helper(root, seq, left, right):
    if root is None:
        return

    node = Node(seq[root])
    node.left = helper(left[root], seq, left, right)
    node.right = helper(right[root], seq, left, right)

    return node