(ns secret-santa-app.databasetest
  (:require [clojure.test :refer :all]
            [secret-santa-app.datasource.sqlutilities :as sql]))

(def my-entity
  {:myEntity {
              :id_column :id
              :name :string
              :age :int
              :address :string}
   })

(def sql-query
  "CREATE table myEntity (sql for columns)")

(deftest sqlutils-test
  (testing "entity table sql generates basic table"
    (is (=
         (sql/entity-table-sql my-entity (fn [n] "sql for columns"))
         sql-query)))

  (testing "generate columns"
    (is (= (set 
            (clojure.string/split
             (sql/generate-column-sql (my-entity :myEntity))
             #", "))
           #{"id_column PRIMARY KEY BIGINT AUTO_GENERATED"
             "name VARCHAR(200)"
             "age INT"
             "address VARCHAR(200)"})))

  (testing "to sql"
    (is (= (sql/to-sql-type [:my-name :int]) 
           "my-name INT"))
    (is (= (sql/to-sql-type [:my-name :bigint]) 
           "my-name BIGINT")))
    (is (= (sql/to-sql-type [:my-name :string]) 
           "my-name VARCHAR(200)"))
    (is (= (sql/to-sql-type [:my-name :id]) 
           "my-name PRIMARY KEY BIGINT AUTO_GENERATED"))

  (testing "save entity sql"
    (is (= (sql/remove-id-column {:id :id :name :string :something :something})
           {:name :string :something :something}))
    (is (= (sql/save-entity-sql :myEntity (my-entity :myEntity))
           "INSERT into myEntity (id, name, age, address) values (?,?,?,?)")))
)
