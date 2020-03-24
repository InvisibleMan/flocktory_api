(ns flocktory_api.server
  (:require [ring.adapter.jetty :as jetty]
            [flocktory_api.config :refer [config]]
            [flocktory_api.app :as app])
  (:gen-class))

(defn start
  []
  (println "Starting server...")
  (let [host (or (config :server-host) "localhost")
        port (or (config :server-port) "8002")
        server (jetty/run-jetty (var app/api-handler)
                                {:host host
                                 :port (Integer/parseInt port)
                                 :join? false})]
    (println "Server started")
    (println (str "You can view the site at http://" host ":" port))
    server))

(defn stop
  [instance]
  (when instance
    (.stop instance))
  (println "Server stopped"))

(defn -main
  []
  (start))
