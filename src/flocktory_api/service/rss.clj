(ns flocktory-api.service.rss
  (:require [feedparser-clj.core :as feeds]
            [clojure.java.io :as io]))

(defn extract [xml]
  (try
    (let [f (feeds/parse-feed (io/input-stream (.getBytes xml)))]
      (map :link (:entries f)))
    (catch Exception e
      '())))
