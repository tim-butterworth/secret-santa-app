(ns secret-santa-app.app
  (:require [ring.adapter.jetty :as jetty]
            [secret-santa-app.datasource.mongodb :as db]))

(defn app [req]
  (println req)
  (db/try-insert)
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello, world"})
