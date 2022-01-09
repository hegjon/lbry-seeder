(ns lbry-seeder.parser)

(defn canonical_urls [content]
  (->> (get-in content ["result" "items"])
      (map #(get % "canonical_url"))))
