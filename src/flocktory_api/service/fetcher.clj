(ns flocktory-api.service.fetcher
  (:require [org.httpkit.client :as httpkit]
            [clojure.tools.logging :as log]
            [clojure.string :as str]))

(def http_opts {
    :headers {
      "accept" "text/html,application/xhtml+xml,application/xml"
      "accept-encoding" "gzip, deflate, br"
      "accept-language" "en-US,en;q=0.9,ru;q=0.8"
      "cookie" "SRCHD=AF=NOFORM; SRCHUID=V=2&GUID=F16D38D2EB6E44F7B6D3B9ACF30B766E&dmnchg=1; SRCHUSR=DOB=20200405; _SS=SID=34D5544A79AC668D23655AE978276749; _EDGE_S=F=1&SID=34D5544A79AC668D23655AE978276749; _EDGE_V=1; MUID=0C5EE3E5C5B3649F0DA5ED46C4386578; MUIDB=0C5EE3E5C5B3649F0DA5ED46C4386578"
      "user-agent" "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) snap Chromium/80.0.3987.149 Chrome/80.0.3987.149 Safari/537.36"
    }})

(defn async-get [url cb]
  (log/debug "Fetch url: '" url "'. Start.")
  (httpkit/get url http_opts (fn [res]
    (let [body (:body res)]
      (log/debug "Fetch url: '" url "'. Finish. Status: " (:status res))
      (cb body)))))
