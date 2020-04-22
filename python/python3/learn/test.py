from collections import deque

def unlock(start, target, deadends):
    queue = deque([start])
    visited = set(deadends)
    level = 0

    while queue:
        for _ in range(len(queue)):
            curr = queue.popleft()
            if curr == target:
                return level

            else:
                for i, wheel in enumerate(curr):
                    lower = curr[:i] + str((int(wheel) - 1) % 10) + curr[i + 1:]
                    higher = curr[:i] + str((int(wheel) + 1) % 10) + curr[i + 1:]

                    if lower not in visited:
                        visited.add(lower)
                        queue.append(lower)
                    if higher not in visited:
                        visited.add(higher)
                        queue.append(higher)

        level += 1

    return None

print(unlock("011","555","999"))
