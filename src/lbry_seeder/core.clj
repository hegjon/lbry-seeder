(ns lbry-seeder.core
  (:gen-class)
  (:require [lbry-seeder.rpc :as rpc]
            [lbry-seeder.parser :as parser]
            [clojure.edn :as edn]))

(defn checkUrls [urls]
  (println "Found" (count urls) "urls" urls))

(defn checkChannel [channel]
  (println "Checking channel" channel)
  (-> channel
      rpc/claim-search
      parser/urls
      checkUrls))

(defn run [config]
  (println config)
  (doseq [channel (:channels config)]
    (checkChannel channel)))

(defn -main
  ([] (throw (RuntimeException. "Missing argument")))
  ([config-file] (-> config-file
                     slurp
                     edn/read-string
                     run)))
