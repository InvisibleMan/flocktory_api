(ns flocktory_api.api.search
  (:require [compojure.core :refer [defroutes GET]]
            [liberator.core :refer [defresource]]
            [flocktory_api.service.json :refer [clj->json]]))

(defn do-search
  [queries]
  {:result {:lenta queries}})

(defresource search
  [request]
  :allowed-methods [:get]
  :available-media-types ["text/html"]
  :handle-ok 
  (fn [req] 
    (let [queries (get-in req [:request :params :query])]
      (do
        (println (str "Parse queries: " queries))
        (println "Start processing..")
        (clj->json (do-search queries))
        )))
  ; #(clj->json (do-search (get-in % [:request :params :query])))
  )

(defroutes search-routes
  (GET "/search" request (search request)))
