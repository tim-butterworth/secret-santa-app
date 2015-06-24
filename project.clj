(defproject secret-santa-app "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [javax.servlet/servlet-api "2.5"]
                 [ring/ring-core "1.3.0"]
                 [ring/ring-jetty-adapter "1.3.0"]
                 [cheshire "5.3.1"]
                 [org.apache.commons/commons-email "1.2"]
                 [restful-router "0.1.0-SNAPSHOT"]
                 [com.h2database/h2 "1.3.170"]]
  :plugins [[lein-ring "0.8.7"]]
  :ring {:handler secret-santa-app.core/app})
