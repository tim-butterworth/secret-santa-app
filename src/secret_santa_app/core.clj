(ns secret-santa-app.core
  (:require [ring.adapter.jetty :as jetty]
            [secret-santa-app.app :as app]))

(defn app [req]
  (app/app req))

(defn start [port]
  (jetty/run-jetty
   app
   {
    :port (Integer. port) 
    :join? false
    }
   ))

(defn -main []
  (let [port (Integer/parseInt
              (or (System/getenv "PORT") "3000"))]
    (start port)))
