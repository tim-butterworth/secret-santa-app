(ns secret-santa-app.loadresources.loadresources
  (:require [clojure.java.io :as io]))

(defn fetch-resource [n]
  (println (str "n: " n))
 (let [resource (io/resource (str "public/" n))]
   (if (= nil resource)
     {:error 404}
     (slurp resource))))
