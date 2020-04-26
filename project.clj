(defproject flocktory_api "0.1.0-SNAPSHOT"
  :description "Test for Flocktory"
  :url "https://gist.github.com/sherpc/22409f4184e039ebbd0ebddd3ee59122"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [ring "1.7.1"]
                 [compojure "1.6.1"]
                 [liberator "0.11.0"]
                 [cheshire "5.9.0"]
                 [http-kit "2.3.0"]
                 [http-kit.fake "0.2.2"]
                 [org.clojure/core.async "0.4.500"]
                 [adamwynne/feedparser-clj "0.5.2"]
                 [lambdaisland/uri "1.2.1"]
                 [org.clojure/tools.logging "1.0.0"]
                 [log4j/log4j "1.2.17" :exclusions [javax.mail/mail
                                                 javax.jms/jms
                                                 com.sun.jmdk/jmxtools
                                                 com.sun.jmx/jmxri]]
                 ]
  :profiles {
    :dev {:dependencies [[org.clojure/tools.namespace "0.3.1"]]
          :source-paths ["dev"]}
    :test {:env {:clj-env :test}}}
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler flocktory_api.app/api-handler}
  :main flocktory-api.server)
