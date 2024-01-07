begin;

declare
curs_p cursor for select * from products;

fetch last from curs_p;

move backward 4 from curs_p;
fetch prior from curs_p;

move backward 7 from curs_p;
fetch prior  from curs_p;

move backward 4 from curs_p;
fetch prior  from curs_p;

fetch first  from curs_p;


close curs_p;
commit;

