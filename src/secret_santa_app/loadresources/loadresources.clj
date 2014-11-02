(ns secret-santa-app.loadresources.loadresources
  (:require [clojure.java.io :as io]))

(defn fetch-resource [n]
  (println n)
  ;(let [resource (:resource n)
  ;      type (:type n)]
    (slurp (io/resource (str "public/" n))))
;)
