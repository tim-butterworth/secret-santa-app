(ns secret-santa-app.views.adminviews
  (:require [secret-santa-app.views.viewutils :as utils]))

(defn register []
  (clojure.string/join
   ""
   ["<html>"
    "<head>"
    "<title>Register</title>"
    (utils/include-core-js)
    (utils/include-core-css)
    "</head>"
    "<body>"
    "<div ng-app='christmasapp'>"
    "<div id='main'>"
    "<div ng-view></div>"
    "</div>"
    "<div ng-app='christmasapp'>"
    "</div>"
    "</div>"
    "</body"
    "</html>"]))
