(ns flocktory_api.server
  (:require [ring.adapter.jetty :as jetty]
            [flocktory_api.config :refer [config]]
            [flocktory_api.app :as app])
  (:gen-class))

(defn start
  []
  (println "[LOG] Starting server...")
  (let [host (or (config :server-host) "localhost")
        port (or (config :server-port) "8002")
        server (jetty/run-jetty (var app/api-handler)
                                {:host host
                                 :port (Integer/parseInt port)
                                 :join? false})]
    (println "[LOG] Server started")
    (println (str "[LOG] You can view the site at http://" host ":" port))
    server))

(defn stop
  [instance]
  (when instance
    (.stop instance))
  (println "[LOG] Server stopped"))

(defn -main
  []
  (start))
