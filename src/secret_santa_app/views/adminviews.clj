(ns secret-santa-app.views.adminviews
  (:require [secret-santa-app.views.viewutils :as utils]
            [secret-santa-app.views.htmlutils :refer :all]))

(defn register []
  (clojure.string/join
   "\n"
   ["<html>"
    "<head>"
    "<title>Register</title>"
    (utils/include-core-js)
    (utils/include-core-css)
    "</head>"
    "<body>"
    (div {:attr
          {"ng-app" "christmasapp"}
          :content (div 
                    {:attr 
                     {"id" "main"}
                     :content (div 
                               {:vals ["ng-view"]}
                               )
                     })

          })
    "</body"
    "</html>"]))
