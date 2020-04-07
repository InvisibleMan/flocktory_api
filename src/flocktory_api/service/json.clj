(ns flocktory_api.service.json
  (:require [cheshire.core :refer [generate-string]]
            [clojure.string :as str]))

(defn clj->json
  "Converts Clojure data to JSON."
  [data]

  (println (str "[LOG] to json: " data))
  (generate-string data {:pretty true}))
