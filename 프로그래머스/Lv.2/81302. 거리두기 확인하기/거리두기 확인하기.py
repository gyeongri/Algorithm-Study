from collections import deque


def solution(places):
    ans = []
    dx = [0, 0, -1, 1]
    dy = [-1, 1, 0, 0]
    q = deque()


    for k in range(5):
        check = False

        for i in range(5):
            if check: break
            for j in range(5):
                if check: break
                if places[k][i][j] == 'P':
                    # (y,x,cnt) 순으로 입력
                    q.append([i, j, 0])
                    # visited[0][0] = 1
                    # 새로운 P에서 탐색을 시작할 때마다 방문체크 배열 새로 만들기
                    visited = [[0 for _ in range(5)] for _ in range(5)]

                    while q:
                        if check: break
                        tmp = q.pop()
                        ty = tmp[0]
                        tx = tmp[1]
                        cnt = tmp[2]
                        visited[ty][tx] = True
                        # print(k," ",tmp)

                        for d in range(4):
                            ny = ty + dy[d]
                            nx = tx + dx[d]
                            # print(f"(ny,nx): {ny} {nx}")

                            # 다음으로 이동할 위치가 맵 이내이고, 벽이 아니고,
                            # 거리가 2 미만이면(2까지 세어야 하므로 1까지만 봄)
                            if -1 < ny < 5 and -1 < nx < 5 and visited[ny][nx]==False and places[k][ny][nx] != 'X' and cnt < 2:
                                # print("dd")
                                if places[k][ny][nx] == 'P':
                                    ans.append(0)

                                    check = True
                                    # print(f"{k}번 라인 거리안지킴!!!!!")
                                    break

                                elif places[k][ny][nx] == 'O':
                                    # print(f"({ny},{nx}) 큐에 다시넣기")
                                    q.append([ny, nx, cnt + 1])
                                    visited[ny][nx] = True
        if check==False:
            # print(f"{k}번째는 잘 지킴")
            ans.append(1)
    return ans