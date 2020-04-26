(ns flocktory-api.server
  (:require [ring.adapter.jetty :as jetty]
            [clojure.tools.logging :as log]
            [flocktory-api.config :refer [config]]
            [flocktory-api.app :as app])
  (:gen-class))

(defn start
  []
  (log/info "Starting server")
  (let [host (or (config :server-host) "localhost")
        port (or (config :server-port) "8002")
        server (jetty/run-jetty (var app/api-handler)
                                {:host host
                                 :port (Integer/parseInt port)
                                 :join? false})]
    (log/info "Server started")
    (log/infof "You can view the site at http://%s:%s" host port)
    server))

(defn stop
  [instance]
  (when instance
    (.stop instance))
  (log/info "Server stopped"))

(defn -main
  []
  (start))
