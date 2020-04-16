(ns flocktory-api.service.fetcher-test
  (:require
   [flocktory-api.service.fetcher :as f]
   [org.httpkit.fake :as fake]
   [clojure.core.async :as async :refer (>!! chan timeout)]
   [clojure.test :refer :all]))

(deftest async-get
  (let [res (atom false)
        url "http://google.com/"
        body "faked"
        expected true
        wait (async/chan)]
    (fake/with-fake-http [url body]
      (f/async-get url (fn [_] 
                           (swap! res (fn [_] true))
                           (async/>!! wait true))))

    (async/alts!! [(async/timeout 200) wait])
      (is (= expected @res))))
