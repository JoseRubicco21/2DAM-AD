package com.ud2_at1.controllers.crud;

import com.ud2_at1.controllers.crud.exceptions.CRUDUnimplementedCreateMethodException;
import com.ud2_at1.controllers.crud.exceptions.CRUDUnimplementedDeleteMethodException;
import com.ud2_at1.controllers.crud.exceptions.CRUDUnimplementedReadMethodException;
import com.ud2_at1.controllers.crud.exceptions.CRUDUnimplementedUpdateMethodException;
import com.ud2_at1.services.logger.Logger;
import com.ud2_at1.services.logger.enums.LogLevel;

public interface CRUD {
    public default boolean create(Operation operation) throws CRUDUnimplementedCreateMethodException{
        throw new CRUDUnimplementedCreateMethodException("Unimplemented CREATE method for this model");
    }
    public default boolean update(Operation operation) throws CRUDUnimplementedUpdateMethodException{
        throw new CRUDUnimplementedUpdateMethodException("Unimplemented UPDATE method for this model");
    }

    public default boolean delete(Operation operation) throws CRUDUnimplementedDeleteMethodException{
        throw new CRUDUnimplementedDeleteMethodException("Unimplemented DELETE method for this model");
    };

    public default boolean read(Operation operation) throws CRUDUnimplementedReadMethodException{
        throw new CRUDUnimplementedReadMethodException("Unimplemented READ method for this model");
    };
 
}
