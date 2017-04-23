(ns rubik.core
  (:require [clojure.core.matrix :as m])
  (:gen-class))

(defn rotate-90 
  [vctr]
  (map int (m/mmul [[0 -1] [1 0]] vctr)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
