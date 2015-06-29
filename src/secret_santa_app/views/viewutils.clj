(ns secret-santa-app.views.viewutils)

(defn js-script [path]
  (str "<script type='text/javascript' src='" path "'></script>"))

(defn css-script [path]
  (str "<link rel='stylesheet' type='text/css' href='" path "'/>" ))

(defn view-path [view]
  (str "/file/javascript/view/" view ".js"))

(defn js-path [file-name]
  (str "/file/javascript/" file-name))

(defn normal-join [lst]
  (clojure.string/join 
   "\n" 
   lst))

(defn resource-mp-join [scripter pather files]
  (normal-join
   (map (fn [n] 
          (scripter (pather n)))
        files)))

(defn include-core-js []
  (let [js-files ["jquery-2.1.4.min.js"
                  "lodash.min.js"
                  "angular.min.js"
                  "angular-route.min.js"
                  "angular-resource.min.js"
                  "bootstrap.min.js"
                  "/app/app.js"
                  "/app/router.js"
                  "/app/controllers/register.js"
                  "/app/services/registrationservice.js"]]
    (resource-mp-join js-script js-path js-files)))

(defn include-core-css []
  (let [css-files ["/file/css/bootstrap.min.css"]]
    (resource-mp-join css-script identity css-files)))

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
