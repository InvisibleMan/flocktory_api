(ns flocktory-api.service.json-test
  (:require
   [flocktory-api.service.json :as js]
   [clojure.test :refer :all]))

(deftest clj->json
  (let [src {:one 2 :two "two" "three" {"ok" true}}
        expected (slurp "fixtures/json.json")
        result (js/clj->json src)]
    (is (= expected result))))
