(ns flocktory_api.model.analyzer
  (:require 
    [flocktory_api.service.queue :as q]
    [flocktory_api.service.fetcher :as f]
    [flocktory_api.service.rss :as rss]
    [clojure.core.async :as async :refer (go >! chan <! close! <!!)]
    [lambdaisland.uri :as uri]
    [clojure.string :as str]))

(def url_template "https://www.bing.com/search?q=%s&format=rss&count=10")
(def MAX_WORKERS 10)

(defn- host2 [link]
  (let [host (:host (uri/uri link))
        parts (str/split host #"\.")]
        (if (> (count parts) 1)
          (str/join "." (take-last 2 parts))
          nil)))

(defn- aggregate [links]
  (let [res (atom {})]
    (reduce 
      (fn [acc link]
        (let [host (host2 link)
              v (get @acc host)
              new-v (if v v #{})]
          (swap! acc assoc host (conj new-v link)))
        acc)
      res links)
    (reduce-kv
      (fn [m k v] (assoc m k (count v))) {} @res)))

(defn calculate-test [queries]
  (let
    [urls (map #(format url_template %) queries)]
    {:result {:lenta urls}}))

(defn calculate [queries]
  (let [urls (map #(format url_template %) queries)
        links (q/work-parallel urls MAX_WORKERS f/async-get rss/extract)]
    (aggregate links)))

