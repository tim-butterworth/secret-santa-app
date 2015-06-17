(ns secret-santa-app.loadresources.loadresources
  (:require [clojure.java.io :as io]))

(defn fetch-resource [n]
  (println (str "n: " n))
    (slurp (io/resource (str "public/" n))))
