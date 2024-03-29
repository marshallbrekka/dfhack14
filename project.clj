(defproject kinder "1.0.0-SNAPSHOT"
  :description "kinder app"
  :url "http://fierce-chamber-4890.herokuapp.com/"
  :license {:name "Eclipse Public License v1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.8"]
                 [ring/ring-jetty-adapter "1.2.2"]
                 [environ "0.5.0"]
                 [org.clojure/tools.logging "0.2.4"]
                 [clj-oauth "1.5.1"]
                 [com.cemerick/url "0.1.1"]
                 [clj-http "1.0.0"]]
  :min-lein-version "2.0.0"
  :plugins [[environ/environ.lein "0.2.1"]]
  :hooks [environ.leiningen.hooks]
  :uberjar-name "kinder.jar"
  :source-paths ["server/src"]
;  :resource-paths ["server/resources"]
;  :target-path "server/target/%s/"
  :main kinder.web
  
  :profiles {:production {:env {:production true}}})
