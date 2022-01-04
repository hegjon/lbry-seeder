(ns lbry-seeder.rpc
   (:require [clj-http.client :as client]
             [clojure.data.json :as json]))

;; $ curl -d'{"method": "claim_search", "params": {"channel": "@MoneroMatteo:b"}}' http://localhost:5279/

(defn request [channel]
  (json/write-str {:method "claim_search", :params {:channel channel :stream_type ["video"]}}))

(defn claim-search [channel]
  (get
    (client/post "http://localhost:5279/" {:body (request channel) :content-type :json})
    :body))

(defn _get-uri [uri]
  (json/write-str {:method "get", :params {:uri uri}}))

(defn get-uri [uri]
  (get
    (client/post "http://localhost:5279/" {:body (_get-uri uri) :content-type :json})
    :body))
