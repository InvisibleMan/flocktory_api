(ns flocktory-api.service.json
  (:require [cheshire.core :refer [generate-string]]
            [clojure.string :as str]))

(defn clj->json
  "Converts Clojure data to JSON."
  [data]

  (generate-string data {:pretty true}))
