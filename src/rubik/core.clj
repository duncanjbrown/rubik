(ns rubik.core
  (:require [clojure.core.matrix :as m])
  (:gen-class))

(defn rotate-90 
  [vctr]
  (map int (m/mmul [[0 -1] [1 0]] vctr)))

(defn swap-faces
  [cubie face1 face2]
  (-> cubie
      (assoc-in [:colors face1] (get-in cubie [:colors face2]))
      (assoc-in [:colors face2] (get-in cubie [:colors face1]))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
