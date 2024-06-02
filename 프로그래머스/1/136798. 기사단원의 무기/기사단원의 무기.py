def solution(number, limit, power):
    # 자신의 번호의 약수 개수만큼 무기를 구매함.
    # 1~N까지 약수개수 구하기
    answer = 0
    for i in range(1, number+1):
        cnt = 0
        for j in range(1,int(i**(1/2)) + 1):#i의 제곱근까지만 반복하기
            if i%j==0:
                cnt+=1
                if j**2 != i: cnt+=1
            if cnt>limit:# limit보다 커지면 바로 끝내기
                cnt = power
                break
        answer += cnt

    return answer