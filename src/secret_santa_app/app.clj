(ns secret-santa-app.app)

(defn app [req]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello, world"})
