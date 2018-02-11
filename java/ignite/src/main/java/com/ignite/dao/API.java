package com.ignite.dao;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.query.FieldsQueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Jopa on 2/11/2018.
 */
public class API {

//    public static void apiInsert(){
//        // Connecting to the cluster.
//        Ignite ignite = Ignition.start();
//
//        // Getting a reference to an underlying cache created for City table above.
//        IgniteCache<Long, City> cityCache = ignite.cache("SQL_PUBLIC_CITY");
//
//        // Getting a reference to an underlying cache created for Person table above.
//        IgniteCache<PersonKey, Person> personCache = ignite.cache("SQL_PUBLIC_PERSON");
//
//        // Inserting entries into City.
//        SqlFieldsQuery query = new SqlFieldsQuery(
//                "INSERT INTO City (id, name) VALUES (?, ?)");
//
//        cityCache.query(query.setArgs(1, "Forest Hill")).getAll();
//        cityCache.query(query.setArgs(2, "Denver")).getAll();
//        cityCache.query(query.setArgs(3, "St. Petersburg")).getAll();
//
//        // Inserting entries into Person.
//        query = new SqlFieldsQuery(
//                "INSERT INTO Person (id, name, city_id) VALUES (?, ?, ?)");
//
//        personCache.query(query.setArgs(1, "John Doe", 3)).getAll();
//        personCache.query(query.setArgs(2, "Jane Roe", 2)).getAll();
//        personCache.query(query.setArgs(3, "Mary Major", 1)).getAll();
//        personCache.query(query.setArgs(4, "Richard Miles", 2)).getAll();
//    }

    public static void apiSelect(){
        // Connecting to the cluster.
        Ignite ignite = Ignition.start();

        // Getting a reference to an underlying cache created for City table above.
        //IgniteCache<Long, City> cityCache = ignite.cache("SQL_PUBLIC_CITY");
        IgniteCache<Long, Object> cityCache = ignite.cache("SQL_PUBLIC_CITY");

        // Querying data from the cluster using a distributed JOIN.
        SqlFieldsQuery query = new SqlFieldsQuery("SELECT p.name, c.name " +
                " FROM Person p, City c WHERE p.city_id = c.id");

        FieldsQueryCursor<List<?>> cursor = cityCache.query(query);

        Iterator<List<?>> iterator = cursor.iterator();

        System.out.println("Query result:");

        while (iterator.hasNext()) {
            List<?> row = iterator.next();

            System.out.println("api    " + row.get(0) + ", " + row.get(1));
        }
    }
}
