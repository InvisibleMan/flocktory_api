(ns flocktory-api.api.search
  (:require [compojure.core :refer [defroutes GET]]
            [liberator.core :refer [defresource]]
            [clojure.tools.logging :as log]
            [flocktory-api.model.analyzer :as analyzer]
            [flocktory-api.service.json :refer [clj->json]]
            ))

(defresource search
  [request]
  :allowed-methods [:get]
  :available-media-types ["application/json"]
  :handle-ok 
  (fn [req] 
    (let [queries (get-in req [:request :params :query])]
      (log/info "Parse queries: " queries)
      (clj->json (analyzer/calculate queries)))))

(defroutes search-routes
  (GET "/search" request (search request)))
