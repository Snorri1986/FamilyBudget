create function i_entertainment(evn_type_id_val integer, amount_val integer, cur_value integer, oper_date date, oper_type character, src_card integer, comm_val character) returns integer
    language plpgsql
as
$$
DECLARE
    last_id int;

BEGIN

    INSERT INTO entertainment(event_type_id,amount,currency,date,source_card,comments,opertype)
    VALUES (evn_type_id_val,amount_val,cur_value,oper_date,src_card,comm_val,oper_type);

    SELECT max(id) INTO last_id FROM entertainment;

    RETURN last_id;

END;
$$;

alter function i_entertainment(integer, integer, integer, date, char, integer, char) owner to u4cg7fn2s82n4v;

