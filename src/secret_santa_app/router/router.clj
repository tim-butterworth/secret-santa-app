(ns secret-santa-app.router.router
  (:require [ring.adapter.jetty :as jetty]
            [restful-router.router.router :as router-builder]
            [restful-router.routermp.helpers :refer :all]
            [secret-santa-app.loadresources.loadresources :as resources]
            [secret-santa-app.views.adminviews :as admin-views]
            [cheshire.core :refer :all]))

(defn simple-message [msg]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body msg})

(defn error-404 []
  {:status 404
   :headers {"Content-type" "text/html"}
   :body "404, invalid request"})

(defn js-response [msg]
  {:status 200
   :headers {"Content-Type" "application/javascript"}
   :body msg})

(defn css-response [msg]
  {:status 200
   :headers {"Content-Type" "text/css"}
   :body msg})

(defn json-status-response [status msg]
  {:status status
   :headers {"Content-Type" "application/json"}
   :body (generate-string msg)})

(defn json-response [msg]
  (json-status-response 200 msg))

(def mp 
  {:routes [(GET 
             "*/secretsanta/admin/:admin/:group/*resourcepath"
             (with-params [admin group resourcepath] 
               (simple-message (clojure.string/join ""
                                                    ["<html>"
                                                     "<head>"
                                                     "<script type='text/javascript' src='/file/javascript/ember.prod.js'></script>"
                                                     "<script type='text/javascript' src='/file/javascript/underscore-min.js'></script>"
                                                     "<script type='text/javascript' src='/file/javascript/angular.min.js'></script>"
                                                     "<head>"
                                                     "<body>"
                                                     "<div>"
                                                     (str "group : " group)
                                                     "</div>"
                                                     "<div>"
                                                     (str "admin : " admin)
                                                     "</div>"
                                                     "<div>"
                                                     (str "loading resource : " resourcepath "......")
                                                     "</div>"
                                                     "</body>"
                                                     "</html>"]))))
            (POST
             "*/register/admin"
             (with-params [params]
;use json from the params
               (do (println (str "params " params)) 
                   (json-response {:success true}))))

            (GET
             "*/file/javascript/*path"
             (with-params [path]
               (js-response 
                (resources/fetch-resource (str"/javascript/" (clojure.string/join "/" path))))))

            (GET
             "*/file/css/*path"
             (with-params [path]
               (css-response 
                (resources/fetch-resource (str"/css/" (clojure.string/join "/" path))))))

            (GET
             "*/secret-santa"
             (with-params []
               (simple-message
                (admin-views/register))))

            (POST
             "*/secret-santa"
             (with-params [params]
               (simple-message (clojure.string/join params))))]

   :Fn (fn [] "failure")
   :default (fn [] (error-404))})

(def router (router-builder/build-router mp))

(defn route [request]
  ((router :route) request))
