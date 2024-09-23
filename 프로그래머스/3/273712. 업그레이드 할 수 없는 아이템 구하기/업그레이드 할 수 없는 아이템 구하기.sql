select i.item_id,ITEM_NAME, RARITY
from ITEM_INFO i left join ITEM_TREE t
    ON i.ITEM_ID = t.PARENT_ITEM_ID
where t.item_id is null
order by i.ITEM_ID desc
