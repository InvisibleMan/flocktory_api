(ns flocktory-api.model.queue
  (:require [org.httpkit.client :as httpkit]
            [clojure.core.async :as async :refer (go >! chan <! close! <!!)]))

(def MAX_REQUEST 10)

(def workers (atom MAX_REQUEST))

(defn async-get [url result]
  (httpkit/get url #(async/go (async/>! result %))))

(def queries ["1" "2" "3" "4" "5" "6" "7" "8" "9" "10" "11"])

(def c (chan 1))

(defn go-loop []
  (let [v (<! c)]
    (if v
      (do
        (println "Got a value in this loop:" v)
        (recur))
      (println "CLOSING"))))

(defn mydo [queries]
  (let [c (chan MAX_REQUEST)]
    (go
      (doseq [q queries] (>! c q))
      (close! c))

    (loop []
      (let [q (<!! c)]
        (if q
          (do
            (go)
            (println "Start download: " q)
            (recur))
          (println "CLOSING"))))))
