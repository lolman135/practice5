package org.practice;

import java.sql.Connection;

public abstract class Table {

    Connection connection;
    DBMediator mediator;

    public Table(Connection connection, DBMediator mediator) {
        this.connection = connection;
        this.mediator = mediator;
    }
}
