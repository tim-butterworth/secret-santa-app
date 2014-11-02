(ns secret-santa-app.views.viewutils)

(defn js-script [path]
  (str "<script type='text/javascript' src='" path "'></script>"))

(defn view-path [view]
  (str "/file/javascript/view/" view ".js"))
(defn include-core-js []
  (let [js-files ["/file/javascript/jquery.js"
                  "/file/javascript/underscore-min.js"
                  "/file/javascript/angular.min.js"]]
    (map (fn [n] (js-script n)) js-files)))
(defn include-view-js [view]
  (js-script (view-path view)))

(defn add-attributes [attrbts]
  (let [ks (keys attrbts)]
    (clojure.string/join " "
     (reduce 
      (fn [accume n] 
        (conj accume (str (name n) "=" (str "'" (attrbts n) "'")))) 
      [""] ks))))

(defn tag-wrap [tag attributes content]
  (clojure.string/join ""
                       ["<"
                        tag
                        (add-attributes attributes)
                        ">"
                        (content)
                        "</"
                        tag
                        ">"]))
