from itertools import combinations, permutations, product

def corresponds(guess, code, score):
    correct = 0
    for guess_digit, real_digit in zip(guess, code):
        if guess_digit == real_digit:
            correct += 1
    return correct == score

def get_candidates(guess, score, possible_codes):
    return [code for code in possible_codes if corresponds(guess, code, score)]

def generate_codes(guess, score):
    codes = []
    wrong_indices = combinations(range(6), 6 - score)
    # for i in wrong_indices:
    #     print(i)
    for indices in wrong_indices:
        digits = []
        for index in range(6):
            if index not in indices:
                digits.append([int(str(guess)[index])])
                continue
            else:
                digits.append([x for x in range(10) if x != str(guess)[index]])
        codes.extend([''.join(map(str, num)) for num in product(*digits)])
    return [code for code in codes if len(set(code)) == 6]

def is_possible(scores):
    scores = sorted(scores.items(), key=lambda x: x[1], reverse=True)
    possible_codes = generate_codes(*scores[0])
    for guess, score in scores[1:]:
        possible_codes = get_candidates(str(guess), score, possible_codes)
    return True if possible_codes else False

is_possible({175286: 2, 293416: 3, 654321: 0})