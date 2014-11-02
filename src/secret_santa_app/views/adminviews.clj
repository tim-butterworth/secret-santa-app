(ns secret-santa-app.views.adminviews
  (:require [secret-santa-app.views.viewutils :as utils]))

(defn register []
  (clojure.string/join
   ""
   ["<html>"
    "<head>"
    "<title>Register</title>"
    (clojure.string/join 
     "\n" 
     (utils/include-core-js))
    (utils/include-view-js "register")
    "</head>"
    "<body>"
    "<div id='content'/>"
    "</body"
    "</html>"]))
