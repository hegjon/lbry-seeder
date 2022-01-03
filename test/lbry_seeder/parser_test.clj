(ns lbry-seeder.parser-test
  (:require [clojure.test :refer :all]
            [lbry-seeder.parser :refer :all]))


(deftest parser
  (testing "Fixture from claim-search"
    (let [content (slurp "test/lbry_seeder/claim-search-response.json")]
      (is (= (urls content)
           ["lbry://@MoneroMatteo#b/nature-walk-salvation-tribu---medium#f"
            "lbry://@MoneroMatteo#b/Tax-AI-Blockchain#f"
            "lbry://@MoneroMatteo#b/SUNDAY-STREAM-4---COMMANDMENTS-BEATITUDE-1#d"
            "lbry://@MoneroMatteo#b/Monero-Bullish-Nothing-Will#e"
            "lbry://@MoneroMatteo#b/Nature-Walk---The-Spirit-of-Pirate-Chain#1"
            "lbry://@MoneroMatteo#b/Petrodollar-Dying---Eurasia-Trade-Zone#1"
            "lbry://@MoneroMatteo#b/nature-walk-medium#0"
            "lbry://@MoneroMatteo#b/Unrealized-Capital-Gains#0"
            "lbry://@MoneroMatteo#b/CRYPTO-BILL-UPDATES#0"
            "lbry://@MoneroMatteo#b/Medical-Mandates---Economic-Exclusion#f"
            "lbry://@MoneroMatteo#b/Crypto-War-Infrastructure-3#e"
            "lbry://@MoneroMatteo#b/Rise-of-CBDCs-+-China-Crypto-Crackdown#6"
            "lbry://@MoneroMatteo#b/Monero-Maximalism#6"
            "lbry://@MoneroMatteo#b/Evolution---Intelligent-Design#5"
            "lbry://@MoneroMatteo#b/Sunday-Stream---Christ-Is-The-True-Lifeboat#4"
            "lbry://@MoneroMatteo#b/shortages-blackmarkets-privacycoins#6"
            "lbry://@MoneroMatteo#b/Monero-News-11-11-Transaction-Volume-ATH#1"
            "lbry://@MoneroMatteo#b/Europe-To-BAN-Proof-of-Work-Mining#2"
            "lbry://@MoneroMatteo#b/Global-Climate-Economic-Revolution---11-10#9"
            "lbry://@MoneroMatteo#b/Monero-News---11-9#5"])))))
