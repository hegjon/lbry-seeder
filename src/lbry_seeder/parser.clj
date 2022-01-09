(ns lbry-seeder.parser
  (:require [clojure.data.json :as json]))

(defn canonical_url [items]
  (map #(get % "canonical_url") items))

(defn urls [content]
  (-> content
      (get "result")
      (get "items")
      canonical_url))
