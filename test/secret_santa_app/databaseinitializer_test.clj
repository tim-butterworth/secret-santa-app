(ns secret-santa-app.databasetest
  (:require [clojure.test :refer :all]
            [secret-santa-app.datasource.databaseinitializer :as sql]))

(def entities sql/entities)

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

(def data
  {:myEntity {
              :name "entity name"
              :age 700
              :address "entity address"
              }
   })

(do
  (sql/register-entity my-entity)
  (def id (sql/save data)))

(deftest test-saving-and-retrieving-data
  (is 
   (= data (sql/find-one id))))
