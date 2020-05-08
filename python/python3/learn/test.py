outlinks = {
    0: [1, 2, 3],
    1: [0],
    2: [0],
    3: [0]
}
inlinks = {site: [] for site in outlinks}
print(inlinks)

num_sites = len(outlinks)
scores = {site: 1.0 / num_sites for site in outlinks}
print(scores)


def update_scores(inlinks, outlinks, scores, d, num_rounds):
    for _ in range(num_rounds):
        new_scores = {}

        for site, neighbors in inlinks.items():
            score = sum([scores[neighbor] / len(outlinks[neighbor]) for neighbor in neighbors])
            new_scores[site] = (1.0 - d) / len(inlinks) + d * score

        scores.update(new_scores)