(ns secret-santa-app.datasource.mongodb
  (:require [monger.core :as mg]
            [monger.collection :as mc])
  (:import [com.mongodb MongoOptions ServerAddress]))

(defn try-insert []
  (let [uri "mongodb://heroku_app27207825:uuemsr8ah041g16p86souq00v9@ds027829.mongolab.com:27829/heroku_app27207825"
        {:keys [conn db]} (mg/connect-via-uri uri)]
    (mc/insert-and-return db "user" {:name "John" :age 30})))
