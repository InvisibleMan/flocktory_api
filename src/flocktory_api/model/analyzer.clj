(ns flocktory-api.model.analyzer
  (:require 
    [flocktory-api.service.queue :as q]
    [flocktory-api.service.fetcher :as f]
    [flocktory-api.service.rss :as rss]
    [clojure.core.async :as async :refer (go >! chan <! close! <!!)]
    [lambdaisland.uri :as uri]
    [clojure.string :as str]))

(def url_template "https://www.bing.com/search?q=%s&format=rss&count=10")
(def MAX_WORKERS 10)

(defn host2 [link]
  (when-let [host (:host (uri/uri link))]
    (let [parts (str/split host #"\.")]
      (if (> (count parts) 1)
        (str/join "." (take-last 2 parts))
        nil))))

(defn aggregate [links]
  (let [res (atom {})]
    (reduce 
      (fn [acc link]
        (when-let [host (host2 link)]
          (let [v (get @acc host #{})]
          (swap! acc assoc host (conj v link))))
        acc)
      res links)
    (reduce-kv
      (fn [m k v] (assoc m k (count v))) {} @res)))

(defn calculate [queries]
  (let [urls (map #(format url_template %) queries)
        links (q/work-parallel urls MAX_WORKERS f/async-get rss/extract)]
    (aggregate links)))

