(require
 '[figwheel-sidecar.repl-api :as ra]
 '[com.stuartsierra.component :as component]
 '[todomvc.system :as system])

(def figwheel-config
  {:figwheel-options {}
   :build-ids ["dev"]
   :all-builds (figwheel-sidecar.config/get-project-builds)
   })

(defrecord Figwheel []
  component/Lifecycle
  (start [config]
    (ra/start-figwheel! config)
    config)
  (stop [config]
    (ra/stop-figwheel!)
    config))

(def sys
  (atom
   (component/system-map
    :figwheel (map->Figwheel figwheel-config)
    :app-server (system/dev-system
                 {:db-uri   "datomic:mem://localhost:4334/todos"
                  :web-port 8081}))))

(defn start []
  (swap! sys component/start))

(defn stop []
  (swap! sys component/stop))

(defn reload []
  (stop)
  (start))

(defn repl []
  (ra/cljs-repl))

;;lein run -m clojure.main --init script/figwheel.clj --repl
