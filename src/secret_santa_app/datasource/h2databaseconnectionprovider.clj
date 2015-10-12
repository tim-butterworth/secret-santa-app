(ns secret-santa-app.datasource.h2database)

(import java.sql.Connection)
(import java.sql.SQLException)
(import java.sql.Statement)
(import java.sql.Connection)
(import java.sql.DriverManager)

(. Class forName "org.h2.Driver")

(def connection 
  (. DriverManager 
     getConnection 
     "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1" "" ""))

(def statement 
  (. connection createStatement))

(. statement execute "CREATE table person (id BIGINT primary key, name VARCHAR(200))")
(. statement execute "Insert into person (id, name) values (1, 'Swathi the Sweet biscuit')")

(def rs 
  (. statement executeQuery "Select * from person"))

(. rs next)
(. rs getString 2)

