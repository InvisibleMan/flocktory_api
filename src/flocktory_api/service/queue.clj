(ns flocktory-api.service.queue
  (:require
    [clojure.tools.logging :as log]
    [clojure.core.async :as async :refer (go >! chan <! close! <!!)]))

(defn work-parallel [queries max_requests worker processor]
  (let [tasks (async/chan)
        workers (async/chan max_requests)
        results (async/chan)
        begin (System/currentTimeMillis)
        res (atom [])]

    ; populate tasks
    (async/go
      (doseq [q queries] (async/>! tasks q))
      (close! tasks))

    ; populate workers pool
    (doseq [i (range max_requests)] (async/>!! workers i))

    (async/go
      (loop []
        (let [q (async/<! tasks)]
          (if q
            (let [w (async/<! workers)]
              (worker q (fn [resp]
                ; (Thread/sleep 3000)
                (async/>!! results (processor resp))
                (async/>!! workers w)
               ))
              (recur))
            (async/close! workers)
            ))))

    ; collect results
    (doseq [i (range (count queries))]
      (swap! res concat (async/<!! results)))

    (async/close! results)
    (log/debug "Fetch time: " (- (System/currentTimeMillis) begin))
    @res
    ))
