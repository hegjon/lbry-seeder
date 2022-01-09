(ns lbry-seeder.rpc-test
  (:require [clojure.test :refer :all]
            [lbry-seeder.rpc :refer :all]
            [clj-http.client :as client]
            [clojure.data.json :as json]))

(defn dummy-page [page]
  (json/write-str {"page" 1
                  "page_size" 20
                  "total_items" 99
                  "total_pages" 5}))

(defn mock-post [url {body :body}]
  {:body (-> body
      json/read-str
      (get "params")
      (get "page")
      dummy-page)})

  (comment let [json (json/read-str body)
        params (get json "params")
        page (get params "page")]
    {:body (str page)})

(deftest correct-body-request
  (testing "claim_search"
    (is (= (_claim-search "@MoneroMatteo:b" 1)
          "{\"method\":\"claim_search\",\"params\":{\"channel\":\"@MoneroMatteo:b\",\"page\":1,\"stream_type\":[\"video\"]}}"))))

(deftest claim-search-paging
  (testing "claim search paging"
    (with-redefs [client/post mock-post]
      (let [actual (claim-search "@mock")]
        (is (= (get actual "page") 1))))))
