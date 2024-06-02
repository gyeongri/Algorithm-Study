def solution(s, skip, index):
    answer = ''
    
    for i in s:
        tmp = ord(i)
        cnt = 0
        flag = 0
        while(True):
            cnt += 1
            tmp2 = ((tmp+cnt)-ord('a'))%26 + ord('a')
            if chr(tmp2) in skip: # 해당 문자가 skip안에 있으면 flag를 증가시키지x
                continue
            else: flag+=1
            if flag==index: break

        
        answer += chr(tmp2)
    return answer