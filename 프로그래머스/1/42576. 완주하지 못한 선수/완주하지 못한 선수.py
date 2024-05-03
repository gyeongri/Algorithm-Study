import collections
def solution(participant, completion):
    d = {name:0 for name in participant}
    for i in participant:
        d[i] += 1
    # print(d)
    for i in completion:
        d[i] -= 1
    # print(d)
    tmp = [k for k,v in d.items() if v != 0]
    for i in tmp: return i