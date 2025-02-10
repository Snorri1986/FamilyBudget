create function i_telecom(t_type_id_val integer, amount_val integer, cur_val integer, oper_date date, oper_type character, src_card integer, comm_val character) returns void
    language plpgsql
as
$$

BEGIN

    IF oper_type = 'Cash' THEN
        INSERT INTO cash_operations_log(optype, amount, date, comments)
        VALUES (0,amount_val,oper_date,comm_val);
    ELSE
        INSERT INTO telecom(t_type_id,amount,currency,date,source_card,comments,opertype)
        VALUES (t_type_id_val,amount_val,cur_val,oper_date,src_card,comm_val,oper_type);
    END IF;

END;
$$;

alter function i_telecom(integer, integer, integer, date, char, integer, char) owner to u4cg7fn2s82n4v;





