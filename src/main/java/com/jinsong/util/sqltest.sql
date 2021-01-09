select c1,c2
from (
select a.c1,b.c2 from tablea a join tableb b
union all
select * from c
)a