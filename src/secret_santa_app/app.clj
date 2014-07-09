(ns secret-santa-app.app)

(defn app [req]
  (println req)
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello, world"})
