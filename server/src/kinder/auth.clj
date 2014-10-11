(ns kinder.auth
  (:require [cemerick.url :as url]
            [clj-http.client :as http]))

(def AUTH_ENDPOINT "https://login.salesforce.com/services/oauth2/authorize")
(def TEST_AUTH_ENDPOINT "https://test.salesforce.com/services/oauth2/authorize")
(def LOGIN_URI "https://login.salesforce.com/services/oauth2/token")
(def TEST_LOGIN_URI "https://test.salesforce.com/services/oauth2/token")

(def KEY "3MVG9xOCXq4ID1uH5GIovTwTVmoLjHQX5gGDh2j5iwt11kAlnxRsyMpCfoFq0KOezC_JbsIKkUdwNM0jQDK1f")
(def SECRET "6512535069274286438")
(def CALLBACK "https://localhost:3000/oauth/_callback")

(defn login-url []
  (-> (url/url AUTH_ENDPOINT)
      (assoc :query {:response_type "code"
                     :client_id KEY
                     :redirect_uri CALLBACK})
      (str)))

(defn authorize
  "Given an auth code returns a map with keys

   :id a url that when requested returns the user object
   :issued_at 
   :instance_url the url that should be queried
   :token_type Bearer
   :id_token don't know what this is for, its super long tho
   :signature also don't know what this is
   :access_token This is what we pass with our api requests"
  [code]
  (->> (http/post LOGIN_URI
                  {:form-params {:client_id KEY
                                 :client_secret SECRET
                                 :grant_type "authorization_code"
                                 :code code
                                 :redirect_uri CALLBACK}
                   :as :json})
       (:body)))

(defn api-call [url access-token]
  (->> (http/get url
                 {:headers {"Authorization" (str "Bearer " access-token)}
                  :as :json
                  :force-redirects true})
       (:body)))
