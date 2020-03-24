(ns flocktory_api.service.json
  (:require [cheshire.core :refer [generate-string]]))

(defn clj->json
  "Converts Clojure data to JSON."
  [data]

  (do
    (println "LOG: TO JSON...")
    (println data)
   (generate-string data)))
