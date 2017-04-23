(ns rubik.core
  (:require [clojure.core.matrix :as m])
  (:gen-class))

(defn rotate-90
  [vctr]
  (vec (map int (m/mmul [[0 -1] [1 0]] vctr))))

(defn swap-faces
  [face1 face2 cubie]
  (-> cubie
      (assoc-in [:colors face1] (get-in cubie [:colors face2]))
      (assoc-in [:colors face2] (get-in cubie [:colors face1]))))

(defn rotate-xy
  [cubie]
  (update cubie :position
          (fn [[x y z]]
            (conj (rotate-90 [x y]) z))))

(defn rotate-x
  [cubies]
  (map (fn [c]
         (-> c
             (rotate-xy)
             ((partial swap-faces :x :y)))) cubies))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
