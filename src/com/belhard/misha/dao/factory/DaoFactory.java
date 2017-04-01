package com.belhard.misha.dao.factory;

import com.belhard.misha.dao.DaoInterface;
import com.belhard.misha.dao.exceptions.UnknownDaoType;
import com.belhard.misha.dao.impl.*;
import com.belhard.misha.entity.AbstractEntity;

public abstract class DaoFactory {

    public static DaoInterface<? extends AbstractEntity> getDao(DaoTypes type){
        switch (type){
            case City: {
                return new DaoCity();
            }
            case Country: {
                return new DaoCountry();
            }
            case Role: {
                return new DaoRole();
            }
            case Route:{
                return new DaoRoute();
            }
            case Status:{
                return new DaoStatus();
            }
            case Ticket:{
                return new DaoTicket();
            }
            case Transport:{
                return new DaoTransport();
            }
            case TransportType:{
                return new DaoTransportType();
            }
            case User:
                return new DaoUser();
        }

        throw new UnknownDaoType("Unknown dao type " + type);
    }
}
