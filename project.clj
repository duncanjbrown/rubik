(defproject rubik "0.1.0-SNAPSHOT"
  :description "Rubik"
  :url "http://example.com/FIXME"
  :license {:name "Creative Commons CC0 1.0 Universal (CC-0)"
            :url "https://creativecommons.org/publicdomain/zero/1.0/legalcode"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot rubik.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
