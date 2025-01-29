create function i_travel(tr_type_id_val integer, amount_val integer, curr_val integer, oper_date date,oper_type character, src_card_val integer, destin_val character, comm_val character) returns integer
    language plpgsql
as
$$
DECLARE
    last_id int;

BEGIN

    INSERT INTO travel(tr_type_id,amount,currency,date,source_card,destination,comments,opertype)
    VALUES (tr_type_id_val,amount_val,curr_val,oper_date,src_card_val,destin_val,comm_val,oper_type);

    SELECT max(id) INTO last_id FROM travel;

    RETURN last_id;

END;
$$;

alter function i_travel(integer, integer, integer, date, char, integer, char, char) owner to u4cg7fn2s82n4v;

