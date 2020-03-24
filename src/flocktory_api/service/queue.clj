(ns flocktory_api.service.queue
  (:require [clojure.core.async :as async :refer :all]))

(def MAX_REQUEST 10)

(def workers (atom MAX_REQUEST))


(defn async-get [url result]
  (org.httpkit.client/get url #(go (>! result %))))


(def queries ["1" "2" "3" "4" "5" "6" "7" "8" "9" "10" "11" ])

(def c (chan 1))


(go-loop []
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
            (println "Start download: " v)
            (recur))
          (println "CLOSING"))))
))


;;
;; A simple in-memory database for testing purpose.
;; 

(def database (atom {}))

;;
;; User management
;; 

(defn get-user
  "Returns the user corresponding to the given username."
  [username]
  (@database username))

(defn add-user
  "Add a new user to database."
  [{:keys [username] :as user}]
  (when (and username
             (not (get-user username)))
    (swap! database assoc (:username user) user)))
