(ns lbry-seeder.core
  (:gen-class)
  (:require [lbry-seeder.rpc :as rpc]
            [lbry-seeder.parser :as parser]))

(defn -main [& args]
  (if (empty? args)
    (throw (RuntimeException. "Missing argument"))
    (let [config (first args)]
      (println config))))

(comment
(if empty? args )
(println (-> "@MoneroMatteo:b"
            rpc/claim-search
            parser/urls)))
