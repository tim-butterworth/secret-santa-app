(ns secret-santa-app.app
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.params :as params]
            [secret-santa-app.router.router :as router]
            [secret-santa-app.utils.requestutils :as utils]))

(defn app [req]
  ((params/wrap-params 
    (fn [request] 
      (router/route 
       (utils/with-json request)))) req))
