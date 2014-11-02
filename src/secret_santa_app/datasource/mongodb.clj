(ns secret-santa-app.datasource.mongodb
  (:require [monger.core :as mg]
            [monger.collection :as mc])
  (:import [com.mongodb MongoOptions ServerAddress]))

(def uri "")
(def conn-db (mg/connect-via-uri uri))
(def db (conn-db :db))
(def conn (conn-db :conn))

(defn try-insert-admin [email]
  (println (mc/insert-and-return db "admin" {:name "Tim"})))
