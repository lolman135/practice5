package org.practice;

import java.sql.Connection;

/**
 * Abstract class {@code Table} representing a database table with a connection and mediator for handling data access.
 */
public abstract class Table {

    Connection connection;
    DBMediator mediator;

    /**
     * Constructor of a {@code Table} with a specified database connection and mediator.
     * @param connection Database connection
     * @param mediator Mediator for managing data access
     */
    public Table(Connection connection, DBMediator mediator) {
        this.connection = connection;
        this.mediator = mediator;
    }
}
