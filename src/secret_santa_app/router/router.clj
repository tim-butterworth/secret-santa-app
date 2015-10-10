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
             "*/secret-santa"
             (with-params []
               (simple-message
                (admin-views/register))))

            (POST
             "*/register/admin"
             (with-params [params json]
               (json-response {:success true :data json})))

            (POST
             "*/group/:group-id/people"
             (with-params [params group-id]
               (json-response {:success true 
                               :json (:json params)})))

            (GET
             "*/group/:group-id/people"
             (with-params [group-id]
               (json-response
                [{:name "p1"} {:name "p2"}])))

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

            (POST
             "*/secret-santa"
             (with-params [params]
               (simple-message (clojure.string/join params))))]

   :Fn (fn [] "failure")
   :default (fn [] (error-404))})

(def router (router-builder/build-router mp))

(defn route [request]
  ((router :route) request))
