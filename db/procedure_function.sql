create or replace procedure p_delete_by_id(p_id integer)
language 'plpgsql'
as $$
begin 
	delete from products where id = p_id;
end;
$$;

call p_delete_by_id(1);



create or replace function f_delete_by_id(f_id integer)
returns void
language 'plpgsql'
as 
$$
begin 
	delete from products where id = f_id;
end;
$$;

select f_delete_by_id(2);
