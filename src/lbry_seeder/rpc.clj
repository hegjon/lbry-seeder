(ns lbry-seeder.rpc
   (:require [clj-http.client :as client]
             [clojure.data.json :as json]))

;; $ curl -d'{"method": "claim_search", "params": {"channel": "@MoneroMatteo:b"}}' http://localhost:5279/

(defn _claim-search [channel page]
  (json/write-str {:method "claim_search", :params {:channel channel :page page :stream_type ["video"]}}))

(defn claim-search [channel]
  (->
    (client/post "http://localhost:5279/" {:body (_claim-search channel 1) :content-type :json})
    :body
    json/read-str))

(defn _get-uri [uri]
  (json/write-str {:method "get", :params {:uri uri}}))

(defn get-uri [uri]
  (:body
    (client/post "http://localhost:5279/" {:body (_get-uri uri) :content-type :json})))
