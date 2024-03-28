SELECT COUNT(ID) AS FISH_COUNT
FROM FISH_INFO T1,
(
SELECT FISH_TYPE
FROM FISH_NAME_INFO
WHERE FISH_NAME IN ('BASS', 'SNAPPER')
 ) AS T2
WHERE T1.FISH_TYPE = T2.FISH_TYPE