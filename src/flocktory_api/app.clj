(ns flocktory_api.app
  (:require [compojure.core :refer [defroutes routes]]
            [compojure.handler :as handler]
            [compojure.route :as route]))

(require '[flocktory_api.api.search :refer [search-routes]])

(defroutes api-handler
  (-> (routes
        search-routes
        (route/not-found "API action not found. Only '/search' action allowed"))
      (handler/api)))
