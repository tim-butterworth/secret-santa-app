(ns secret-santa-app.views.htmlutils)

(defn element-properties [properties] 
  (clojure.string/join
   (map #(str " " %)
        (filter #(not (= "" %))
                [(clojure.string/join 
                  " "
                  (map 
                   (fn [entry] 
                     (str "" (entry 0) "='" (entry 1) "'")) 
                   properties))]))))

(defn element 
  ([name] 
   (element name {}))
  ([name properties] 
   (let [content (:content properties)
         attr (:attr properties)
         vals (:vals properties)]
     (str 
      "<" name (element-properties attr) 
      (if (= nil vals)
        ""
        (str " "
             (clojure.string/join 
              " " 
              vals))) 
      ">"
      content
      "</" name ">"))))

(defn div 
  ([]
   (element "div"))
  ([properties]
   (element "div" properties)))
