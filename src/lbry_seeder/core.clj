(ns lbry-seeder.core
  (:gen-class)
  (:require [lbry-seeder.rpc :as rpc]
            [lbry-seeder.parser :as parser]
            [clojure.edn :as edn]))

(defn checkUrls [urls]
  (println "Found" (count urls) "urls")
  (doseq [uri urls]
    (println "Downloading uri:" uri)
    (rpc/get-uri (first urls))))

(defn checkChannel [channel]
  (println "Checking channel" channel)
  (-> channel
      rpc/claim-search
      parser/urls
      checkUrls))

(defn run [{channels :channels}]
  (println channels)
  (doseq [channel channels]
    (checkChannel channel)))

(defn -main
  ([] (do (println "Missing argument config-file")
          (System/exit 22)))
  ([config-file] (-> config-file
                     slurp
                     edn/read-string
                     run)))
