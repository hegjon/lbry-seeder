(ns lbry-seeder.rpc-test
  (:require [clojure.test :refer :all]
            [lbry-seeder.rpc :refer :all]))


(deftest rpc
  (testing "claim_search"
    (is (= (request "@MoneroMatteo:b")
          "{\"method\":\"claim_search\",\"params\":{\"channel\":\"@MoneroMatteo:b\",\"stream_type\":\"video\"}}"))))
