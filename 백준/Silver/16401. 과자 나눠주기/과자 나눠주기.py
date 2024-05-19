import sys
M, N = map(int, input().split())
arr = sorted(list(map(int, input().split())))
if M>sum(arr): x=0
else:
    left = 1
    right=arr[-1]
    while (left<=right):
        cnt = 0
        mid = (left+right)//2
        if mid == 0:
            total = 0
            break
        for i in range(N):
            cnt += arr[i]//mid
        if cnt>=M:
            x=mid
            left=mid+1
        else:
            right=mid-1
print(x)