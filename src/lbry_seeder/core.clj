(ns lbry-seeder.core
  (:gen-class)
  (:require [lbry-seeder.rpc :as rpc]
            [lbry-seeder.parser :as parser]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (-> "@MoneroMatteo:b"
              rpc/claim-search
              parser/urls)))
