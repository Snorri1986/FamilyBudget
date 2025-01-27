create function i_health(h_type_id_val integer, amount_val integer, cur_val integer, oper_date date, oper_type character, src_card integer, comm_val character) returns integer
    language plpgsql
as
$$
DECLARE
    last_id int;

BEGIN

    INSERT INTO health(h_type_id,amount,currency,date,source_card,comments,opertype)
    VALUES (h_type_id_val,amount_val,cur_val,oper_date,src_card,comm_val,oper_type);

    SELECT max(id) INTO last_id FROM health;

    RETURN last_id;

END;
$$;

alter function i_health(integer, integer, integer, date, char, integer, char) owner to u4cg7fn2s82n4v;

