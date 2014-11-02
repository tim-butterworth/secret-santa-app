(ns secret-santa-app.utils.requestutils
  (:require [cheshire.core :refer :all]))

(import java.io.InputStreamReader)
(import java.io.BufferedReader)

(def json-type "application/json")
(def content-type-keys 
  [:headers
   "content-type"])

(defn get-content-type [req]
  (reduce (fn [accume n] (accume n)) req content-type-keys))

(defn stream-to-string [reader]
  (loop [i (. reader read) accume []]
    (if (not (= i -1))
      (recur (. reader read) (conj accume (char i)))
      (clojure.string/join "" accume))))

(defn json-to-params [req]
  (with-open [reader (InputStreamReader. (req :body))
              b-reader (BufferedReader. reader)]
    (let [json (parse-string (stream-to-string b-reader))]
        (assoc req :params 
               (assoc (req :params) :json json)))))

(defn is-json [req]
  (. json-type equalsIgnoreCase 
     (get-content-type req)))

(defn with-json [req]
  (if (is-json req)
    (json-to-params req)
    req))
