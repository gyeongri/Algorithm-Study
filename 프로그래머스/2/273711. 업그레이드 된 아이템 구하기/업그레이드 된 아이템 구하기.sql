SELECT ITEM_ID, ITEM_NAME, RARITY
FROM ITEM_INFO NATURAL JOIN ITEM_TREE
WHERE ITEM_TREE.PARENT_ITEM_ID IN (
    SELECT ITEM_ID
    FROM ITEM_INFO
    WHERE RARITY = 'RARE')
ORDER BY ITEM_ID DESC