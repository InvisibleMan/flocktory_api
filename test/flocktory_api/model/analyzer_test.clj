(ns flocktory-api.model.analyzer-test
  (:require
   [flocktory-api.model.analyzer :as az]
   [clojure.test :refer :all]))

(deftest host2
  (let [groups [["https://interner.yandex.ru" "yandex.ru"]
                ["http://dx.ui.prog.com" "prog.com"]
                ["rus.m.wikipedia.org" nil]
                ]]
    (doall (map (fn [[src expected]] (is (= expected (az/host2 src)))) groups))
))

(deftest aggregate 
  (let [links ["http://lenta.ru?q=sdfs"
               "http://mobile.lenta.ru/posts/one"
               "http://api.mobile.lenta.ru/posts/one?adfsf=34" 
               "http://wikipedia.org/clojure" 
               "http://m.wikipedia.org/scala" 
               "http://blog.planet.clojure.org/posts/new?order=desc" ]
        expected {"lenta.ru" 3 "wikipedia.org" 2 "clojure.org" 1}]
        (is (= expected (az/aggregate links)))))
