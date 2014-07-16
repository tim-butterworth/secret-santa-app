(ns secret-santa-app.app
  (:require [ring.adapter.jetty :as jetty]
            [secret-santa-app.datasource.mongodb :as db]
            [secret-santa-app.email.email :as email]))

(defn simple-message [msg]
  {:status 200
     :headers {"Content-Type" "text/plain"}
     :body msg})
(defn app [req]
  (println req)
  (if (= :get (req :request-method))
    (simple-message "hi there")
    (do
      (email/send-email)
      (simple-message "sent an email"))))
