(ns secret-santa-app.datasource.databaseinitializer
  (:require [secret-santa-app.datasource.sqlutilities :as sql]))

(def entities 
  (atom {}))

(def created-entity-tables
  (atom false))

(defn register-entity 
  ([entity] (register-entity entity entities))
  ([entity entity-mp] 
     (swap! entity-mp
            (fn [mp]
              (assoc 
                  mp
                (first (keys entity)) 
                (first (vals entity)))))))

(defn create-entity-tables [])

(defn configure [datasource entities ])
(defn save [data])
(defn find-one [id])
