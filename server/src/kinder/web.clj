(ns kinder.web
  (:require [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.handler :refer [site]]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [clojure.tools.logging :as lg]
            [ring.adapter.jetty :as jetty]
            [environ.core :refer [env]]))

(defn ->response [content-type body & [status]]
  (let [content (case content-type
                  :html "text/html"
                  :json "text/json")]
    {:status (or status 200)
     :headers {"Content-Type" content}
     :body body}))

(defn ->json [body & [status]]
  (->response :json body status))

(defn ->html [body & [status]]
  (->response :html body status))

(defn splash []
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (pr-str ["Hello" :from 'Heroku])})

(defroutes app
  (GET "/" []
       (->html "Hello World"))

  (GET "/auth/oath_redirect" request
       (lg/info "oauth_called" request)
       (->html "Ok!"))
  (ANY "*" []
       (route/not-found (slurp (io/resource "404.html")))))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))

;; For interactive development:
;; (.stop server)
;; (def server (-main))
