(defproject om-next-demo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :jvm-opts ^:replace ["-Xms2g" "-Xmx2g" "-server"]

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.339"]
                 [com.datomic/datomic-free "0.9.5697" :exclusions [com.google.guava/guava]]
                 [bidi "2.1.3" :exclusions [ring/ring-core]]
                 [org.omcljs/om "1.0.0-alpha46"]
                 [ring/ring "1.6.3"]
                 [com.cognitect/transit-clj "0.8.309"]
                 [com.cognitect/transit-cljs "0.8.256"]
                 [com.stuartsierra/component "0.3.2"]
                 ;; [com.cemerick/piggieback "0.2.2"]
                 [com.bhauman/rebel-readline "0.1.4"]
                 [figwheel-sidecar "0.5.17"]
                 ]
  :clean-targets ^{:protect false} ["resources/public/js"]
  :source-paths ["src/clj" "src/cljs"]

  :plugins [[lein-figwheel "0.5.17"]
            ;; [lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]

            ]


  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src/cljs"]
                ;; :figwheel {:on-jsload "todomvc.core/on-js-reload"
                ;;            :open-urls ["http://localhost:3449"]}
                :figwheel true

                :compiler {:main "todomvc.core"
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/app.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true
                           :optimizations      :none
                           :static-fns         true
                           :optimize-constants true
                           :pretty-print       true
                           :externs            ["src/js/externs.js"]
                           :closure-defines    {"goog.DEBUG" false}
                           :verbose            true
                           :preloads [devtools.preload]}}
               {:id "min"
                :source-paths ["src"]
                :compiler {:output-to "resources/public/js/compiled/app.js"
                           :main "todomvc.core"
                           :optimizations :advanced
                           :pretty-print false}}]}

  :figwheel {:css-dirs ["resources/public/css"]}

  :profiles {:dev {:dependencies [[binaryage/devtools "0.9.10"]
                                  [figwheel-sidecar "0.5.17" ]
                                  ;; [com.cemerick/piggieback "0.2.2"]
                                  ;;[cider/piggieback "0.3.6"]
                                  ]
                   ;; need to add dev source path here to get user.clj loaded
                   :source-paths ["src/dev"]
                   ;; :plugins [[cider/cider-nrepl "0.18.0"]]
                   ;;                :repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]}
                   ;;:repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   ;; need to add the compliled assets to the :clean-targets
                   ;; :clean-targets ^{:protect false} ["resources/public/js" :target-path]

                   }})
