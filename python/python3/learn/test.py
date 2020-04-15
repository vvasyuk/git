def pick_fruit(trees):
    a, b = trees[0], trees[1]

    last_picked = b
    last_picked_count = (a == b)
    max_length_path = curr = 1

    for tree in trees[1:]:
        if tree not in (a, b):
            a, b = last_picked, tree
            last_picked = tree
            curr = last_picked_count + 1
        else:
            curr += 1
            if last_picked == tree:
                last_picked_count += 1
            else:
                last_picked = tree
                last_picked_count = 1

        max_length_path = max(curr, max_length_path)

    return max_length_path

print(pick_fruit([2, 1, 2, 3, 3, 1, 3, 5]))