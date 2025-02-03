create function i_housing_rent(hr_type_id integer, amount_val integer, cur_val integer, oper_date date, oper_type character, src_card integer, comm_value character) returns void
    language plpgsql
as
$$

BEGIN

    INSERT INTO housing_rent(hr_type_id,amount,currency,date,source_card,comments,opertype)
    VALUES (hr_type_id,amount_val,cur_val,oper_date,src_card,comm_value,oper_type);

END;
$$;

alter function i_housing_rent(integer, integer, integer, date, char, integer, char) owner to u4cg7fn2s82n4v;

