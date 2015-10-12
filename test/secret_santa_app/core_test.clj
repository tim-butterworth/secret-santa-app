(ns secret-santa-app.core-test
  (:require [clojure.test :refer :all]
            [secret-santa-app.views.htmlutils :refer :all]
            [secret-santa-app.datasource.databaseinitializer :refer :all]
            [secret-santa-app.datasource.sqlutilities :as sql]))

(def simplest
  (div))

(def full
  (div 
   {
    :content "content" 
    :attr {
           "hi" "there"
           }
    :vals ["some-val"]}))

(def vals-only
  (div 
   {
    :vals ["ng-something" "ng-something-else"]
    }))

(def content-only
  (div
   {
    :content "some cool content"
    }))

(def attr-only
  (div
   {
    :attr {"some" "attr"
           "another" "attr2"}
    }))

(deftest a-test
  (testing "htm generation"
    (is (=  full "<div hi='there' some-val>content</div>")))
  (testing "simplest"
    (is (= simplest "<div></div>")))
  (testing "vals-only"
    (is (= vals-only "<div ng-something ng-something-else></div>")))
  (testing "content-only"
    (is (= content-only "<div>some cool content</div>")))
  (testing "attr-only"
    (is (or
         (= attr-only "<div some='attr' another='attr2'></div>")
         (= attr-only "<div another='attr2' some='attr'></div>")))))

(def my-entity
  {:myEntity {
              :id_column :id
              :name :string
              :age :int
              :address :string}
   })

(def sql-query
  "CREATE table myEntity (sql for columns)")

(deftest database-test
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
    (is (= (sql/to-sql [:my-name :int]) 
           "my-name INT"))
    (is (= (sql/to-sql [:my-name :bigint]) 
           "my-name BIGINT")))
    (is (= (sql/to-sql [:my-name :string]) 
           "my-name VARCHAR(200)"))
    (is (= (sql/to-sql [:my-name :id]) 
           "my-name PRIMARY KEY BIGINT AUTO_GENERATED"))
  )

(deftest test-register-entity
  (testing "entities map is initially empty"
    (is (empty? @entities)))
  (testing "add one entity"
    (do
      (register-entity {:myEntity "some stuff"})
      (is (= @entities 
             {:myEntity "some stuff"}))))
  (testing "add multiple entities"
    (do
      (register-entity {:myEntity "some stuff"})
      (register-entity {:secondEntity "more stuff"})
      (register-entity {:thirdEntity "more more stuff"})
      (is (= @entities
             {:myEntity "some stuff"
              :secondEntity "more stuff"
              :thirdEntity "more more stuff"}))))
  )

(deftest test-configure
  (testing ""))
